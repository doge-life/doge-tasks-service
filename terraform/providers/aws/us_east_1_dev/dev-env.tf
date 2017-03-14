variable "ami_id" {}
variable "doge_private_key_file" {}
variable "artifactory_credentials" {}

provider "aws" {
    region = "us-east-1"
}

resource "aws_instance" "doge-task-service-dev-env" {
    ami = "${var.ami_id}"
    instance_type = "t2.micro"
    vpc_security_group_ids = ["${aws_security_group.doge-task-service-dev.id}"]
    tags { Name = "Doge task-service - Development Environment" }
    key_name = "doge-default"
}

resource "null_resource" "deploy-doge-task-service-dev" {

    /*
      This trigger section forces this resource to be applied each time, since the timestamp
      will always be different. Long term, this should be replaced with a unique version number
      generated for each artifact.
    */
    triggers {
        current_time = "${timestamp()}"
    }

    connection {
        user = "ubuntu"
        private_key = "${file(var.doge_private_key_file)}"
        host = "${aws_instance.doge-task-service-dev-env.public_ip}"
    }

    provisioner "file" {
        source = "import_artifact"
        destination = "/tmp/import_artifact"
    }

    provisioner "remote-exec" {
        inline = [
            "chmod +x /tmp/import_artifact",
            "export ARTIFACTORY_CREDENTIALS=${var.artifactory_credentials}",
            "/tmp/import_artifact"
        ]
    }
}

resource "aws_security_group" "doge-task-service-dev" {
    name = "doge-task-service-dev"

    ingress {
      from_port = 80
      to_port = 80
      protocol = "TCP"
      cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
      from_port = 8080
      to_port = 8080
      protocol = "TCP"
      cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
      from_port = 22
      to_port = 22
      protocol = "TCP"
      cidr_blocks = ["0.0.0.0/0"]
    }

    egress {
      from_port = 0
      to_port = 0
      protocol = "-1"
      cidr_blocks = ["0.0.0.0/0"]
    }

}
