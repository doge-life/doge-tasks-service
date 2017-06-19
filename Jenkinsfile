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
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t doge-life/doge-tasks-service .' 
            }
        }
        stage('Publish to Registry') {
            steps {
              sh "scripts/publishToRegistry.sh ${NEXUS_CREDENTIALS_USR} ${NEXUS_CREDENTIALS_PSW} ${NEXUS_REGISTRY_URL}"
            }
        }
        stage('Update latest tag') {
            when { branch 'master' }
            steps {
                sh "scripts/publishToRegistry.sh ${NEXUS_CREDENTIALS_USR} ${NEXUS_CREDENTIALS_PSW} ${NEXUS_REGISTRY_URL} latest"
            }
        } 
    }
}
