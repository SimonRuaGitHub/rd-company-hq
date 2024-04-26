start-local:
	docker-compose -f ./docker/local-rd-stock/docker-compose.yaml up --detach --build

stop-local:
	docker-compose -f ./docker/local-rd-stock/docker-compose.yaml down --volumes --rmi all --remove-orphans || true

