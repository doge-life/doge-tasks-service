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
    }
}
