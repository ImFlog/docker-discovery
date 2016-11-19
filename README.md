# docker-discovery

This repository explores various discovery technics for microservices architectures using Spring Cloud or Docker tools.

The used application is composed of two services:
* Slip : Has a synchronized endpoint which only sleep for 100ms.
* Poule : Continously call Slip and graph the speed.

The idea is that a contention exists in the Slip service, we need to introduce disovery mecanisms to allow us to scale
and improve our speed.