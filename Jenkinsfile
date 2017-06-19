#!groovy

node {
    checkout scm

    withCredentials([
        usernamePassword(usernameVariable: 'MAVEN_NEXUS_USR', passwordVariable: 'MAVEN_NEXUS_PSW', credentialsId: 'nexus-credentials'),
        usernamePassword(usernameVariable: 'MAVEN_PROXY_USR', passwordVariable: 'MAVEN_PROXY_PSW', credentialsId: 'maven-proxy')
        ]) {

        stage('Unit Tests') {
            sh './gradlew clean test'
        }
        stage('Build Docker Image') {
            sh 'docker build -t doge-life/doge-tasks-service .' 
        }
        stage('Publish to Registry') {
            sh "scripts/publishToRegistry.sh ${NEXUS_CREDENTIALS_USR} ${NEXUS_CREDENTIALS_PSW} ${NEXUS_REGISTRY_URL}"
        }
        if (env.BRANCH_NAME == 'master') {
          stage('Update latest tag') {
              sh "scripts/publishToRegistry.sh ${NEXUS_CREDENTIALS_USR} ${NEXUS_CREDENTIALS_PSW} ${NEXUS_REGISTRY_URL} latest"
          } 
        }
      }
}
