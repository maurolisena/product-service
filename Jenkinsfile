pipeline {
    agent any

    stages {

        stage('Limpiar contenedores y volúmenes') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                    sh '''
                        docker compose down --volumes --remove-orphans
                        docker volume rm mongo_database_data
                    '''
                }
            }
        }

        stage('Construir y levantar servicios') {
            steps {
                echo "Construyendo imágenes y levantando servicios en background"
                sh 'docker compose up -d --build'
            }
        }

        stage('Esperar a MongoDB listo') {
            steps {
                echo "Esperando que MongoDB esté saludable..."
                sh '''
                    until docker exec mongo_database mongosh --eval "db.runCommand('ping').ok" | grep 1; do
                        echo "Esperando MongoDB..."
                        sleep 5
                    done
                '''
            }
        }

        stage('Verificar contenedores y logs de mongo') {
            steps {
                echo "Listando contenedores activos"
                sh 'docker ps'

                echo "Mostrando últimos logs de mongo_database para verificar scripts init"
                sh 'docker logs --tail 30 mongo_database'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado'
        }
    }
}