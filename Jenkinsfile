pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'echo $PWD'
            }
        }
        stage('SonarCube') {
            steps {
               sh 'gradlew.bat sonarqube -Dsonar.projectKey=cordamvp -Dsonar.host.url=http://host.docker.internal:9000 -Dsonar.login=041be7a281c9d58ba540b981d082710d2774ef7a'
            }
        }
    }
}