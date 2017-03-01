#!groovy
pipeline {
    agent any

    environment {
        ORG_GRADLE_PROJECT_ARTIFACTORY = credentials('artifactory-deploy')
    }
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
        stage('Deploy to Artifactory') {
            steps {
                sh './gradlew publish'
            }
        }
    }
}
