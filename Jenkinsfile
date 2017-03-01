#!groovy
pipeline {
    agent any

    stages {
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
        stage('Publish to Artifactory') {
            steps {
                script {
                    def server = Artifactory.server 'Doge Artifactory'
                    def rtGradle = Artifactory.newGradleBuild()
                    rtGradle.deployer server: server, repo: 'app-releases-local'
                    rtGradle.useWrapper = true
                    def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'clean artifactoryPublish'
                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
}
