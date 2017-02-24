pipeline {
    agent any

    stages {
        stage('Gradle') {
            steps {
                sh './gradlew clean check'
            }
        }
        stage('Static Analysis') {
            steps {
                sh './gradlew pmdMain'
            }
        }
    }
}
