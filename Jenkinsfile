#!groovy

pipeline {
    agent any

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
