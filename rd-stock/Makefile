start-local:
	docker compose -f ./docker/local-rd-stock/docker-compose.yaml up --detach --build

stop-local:
	docker compose -f ./docker/local-rd-stock/docker-compose.yaml down --volumes --rmi all --remove-orphans || true

database-terminal:
	docker exec -it rd-stock-maria-db /bin/bash