pipeline {
    agent any

    tools {
        maven 'Maven3'   
        jdk 'Java17'     
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/illiyanabahari/demo-app.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
               bat "copy /Y target\\demo-app-0.0.1-SNAPSHOT.war C:\\apache-tomcat-10\\webapps\\demo-app.war"
            }
        }
    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Something went wrong...'
        }
    }
}