Docker : Build, Ship and Run

Once your images have been builded (see the docker base directory) you have to push them on a public registry.
1. exec `sudo docker tag slip:latest <username>/slip:latest`
2. exec `sudo docker push <username>/slip:latest`
3. exec `sudo docker tag poule:latest <username>/poule:latest`
4. exec `sudo docker push <username>/poule:latest`

For the next steps, you need multiple servers that can communicate with each other
### Swarm cluster creation
1. Connect to the first server that will be the swarm leader
2. exec `sudo docker swarm init`
3. This command will display a Docker swarm join command with the appropriate token
4. Connect to others servers to execute this command

### Multi-Host network
With Docker swarm, no need an external KV store for a Docker overlay network
1. Connect to the swarm leader server (ssh)
2. exec `sudo docker network create --driver overlay swarm-demo`
3. Verify that your network is created : `sudo docker network ls`

### Services creation
1. Connect to the swarm leader server (ssh)
2. exec `sudo docker service create --name slip --network swarm-demo <username>/slip:latest`
3. exec `sudo docker service create --name poule --network swarm-demo --publish 8080:8080 <username>/poule:latest`
4. Verify your containers are running : `sudo docker service ls`
5. You can now access any node on port 8080

### Scale your application
1. Connect to the swarm leader server (ssh)
2. exec `sudo docker service update slip --replicas 3`
3. Check the result on the Web UI

### Stop services
1. Connect to the swarm leader server (ssh)
2. exec `sudo docker service rm poule`
3. exec `sudo docker service rm slip`