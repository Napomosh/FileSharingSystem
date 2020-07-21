pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
                sh 'mvn package'
                echo 'Build...'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
