#!groovy
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
