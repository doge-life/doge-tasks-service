#!groovy

def getAMIFromPackerManifest() {
    def workspace = pwd()
    def manifest = new File("${workspace}/packer/manifest.json")
    def json = new groovy.json.JsonSlurper().parseText(manifest.text)
    def ami_info = json.builds.first().artifact_id.split(':')
    ami_info[1]
}

pipeline {
    agent any

    environment {
        ORG_GRADLE_PROJECT_ARTIFACTORY = credentials('artifactory-deploy')
        AWS_ACCESS_KEY_ID = "AKIAJIAYKGAD7SZSF6FQ"
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-key')
    }
    stages {
        stage('Clean workspace') {
            steps {
                sh 'git clean -fdx'
            }
        }
        stage('Unit Tests') {
            steps {
                sh './gradlew clean test'
            }
        }
        stage('Static Analysis') {
            steps {
                sh './gradlew pmdMain'
                archiveArtifacts artifacts: '**/build/reports/**', fingerprint: true
            }
        }
        stage('Deploy to Artifactory') {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew publish'
            }
        }
        stage('Build and Verify Images') {
            steps {
                sh './packer/build'
            }
        }
        stage('Deploy to Dev') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([file(credentialsId: 'doge-private-key-file', variable: 'TF_VAR_doge_private_key_file'),
                                 usernameColonPassword(credentialsId: 'artifactory-deploy', variable: 'TF_VAR_artifactory_credentials')]) {
                    sh "./terraform/providers/aws/us_east_1_dev/plan ${getAMIFromPackerManifest()}"
                    sh "./terraform/providers/aws/us_east_1_dev/apply ${getAMIFromPackerManifest()}"
                    archiveArtifacts artifacts: "**/terraform.tfstate"
                }
            }
        }
        stage('Update Git with Latest Image') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([string(credentialsId: 'github-oauth', variable: 'GITHUB_TOKEN')]) {
                    sh './packer/update_git'
                }
            }
        }
    }
}
