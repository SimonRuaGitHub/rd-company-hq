start-local-env:
	docker-compose -f ./docker/local/docker-compose.yaml up --detach --build

stop-local-env:
	docker-compose -f ./docker/local/docker-compose.yaml down --volumes --rmi all --remove-orphans || true

