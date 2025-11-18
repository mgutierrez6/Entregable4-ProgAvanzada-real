pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo ">>> Clonando el repositorio..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo ">>> Ejecutando mvn clean package (sin tests por ahora)..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo ">>> Ejecutando tests unitarios..."
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo ">>> Empaquetando WAR final..."
                bat 'mvn clean package'
            }
        }

        stage('Deploy con Tomcat (Windows)') {
            steps {
                echo ">>> Ejecutando script de deployment para Windows..."

                bat """
                    deploy-windows.bat
                """
            }
        }
    }

    post {
        success {
            echo ">>> Pipeline completado con éxito ✔"
        }
        failure {
            echo ">>> Falló el pipeline ✘ — revisar logs"
        }
    }
}
