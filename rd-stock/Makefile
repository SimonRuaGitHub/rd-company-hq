start-local:
	docker compose -f ./docker/local-rd-stock/docker-compose.yaml up --detach --build

stop-local:
	docker compose -f ./docker/local-rd-stock/docker-compose.yaml down --volumes --rmi all --remove-orphans || true

running-container:
	docker compose -f ./docker/local-rd-stock/docker-compose.yaml up --detach --build $(DOCKER_SERVICE)

database-terminal:
	docker exec -it rd-stock-maria-db /bin/bash

package-only:
	mvn clean package -DskipTests