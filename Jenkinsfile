pipeline {
    agent any

    stages {
        stage('Preparar') {
            steps {
                echo "Limpiando contenedores previos si existen..."
                sh '''
                    docker compose down --volumes --remove-orphans || true
                '''
            }
        }

        stage('Build y Levantar Servicios') {
            steps {
                echo "Construyendo imágenes y levantando servicios"
                sh 'docker compose up -d --build'
            }
        }

        stage('Esperar MongoDB') {
            steps {
                echo "Esperando a que MongoDB esté listo..."
                sh '''
                    until docker exec mongo_database mongosh --eval "db.runCommand('ping').ok" | grep 1; do
                        echo "Esperando MongoDB..."
                        sleep 5
                    done
                '''
            }
        }

        stage('Verificar Contenedores') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado'
        }
    }
}