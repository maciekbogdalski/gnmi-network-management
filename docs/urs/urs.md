
### User Requirements Specification Document
##### DIBRIS – Università di Genova. Scuola Politecnica, Software Engineering Course 80154


**VERSION : 5.0**

**Authors**  
Maciej Bogdalski
Soodeh Bakrani

**REVISION HISTORY**

| Version    | Date        | Authors      | Notes        |
| ----------- | ----------- | ----------- | ----------- |
| 1.0 | 2023-03-29 | Maciej Bogdalski, Soodeh Bakrani | First draft |
| 2.0 | 2023-04-06 | Maciej Bogdalski, Soodeh Bakrani | Content accepted by the client after corrections |
| 3.0 | 2023-04-17 | Maciej Bogdalski, Soodeh Bakrani | Changed layout |
| 4.0 | 2023-04-28 | Maciej Bogdalski, Soodeh Bakrani | Corrections in Functional Requirements. URS accepted.  |
| 5.0 | 2023-05-29 | Maciej Bogdalski, Soodeh Bakrani | Corrections in Functional Requirements.|

# Table of Contents

1. [Introduction](#p1)
	1. [Document Scope](#sp1.1)
	2. [Definitios and Acronym](#sp1.2) 
	3. [References](#sp1.3)
2. [System Description](#p2)
	1. [Context and Motivation](#sp2.1)
	2. [Project Objectives](#sp2.2)
3. [Requirement](#p3)
 	1. [Stakeholders](#sp3.1)
 	2. [Functional Requirements](#sp3.2)
 	3. [Non-Functional Requirements](#sp3.3)
  
  

<a name="p1"></a>

## 1. Introduction

<a name="sp1.1"></a>

### 1.1 Document Scope
This User Requirements Specification (URS) document outlines the requirements for the micro-service implementing a Java/Groovy gNMI/gRPC client to install, manipulate, and delete the configuration of network devices and to view the operational data. 

<a name="sp1.2"></a>

### 1.2 Definitios and Acronym


| Acronym				| Definition | 
| ------------------------------------- | ----------- | 
| gNMI                                  | gRPC Network Management Interface |
| gRPC                                  | general-purpose Remote Procedure Call  |
| URS                                  | User Requirements Specification |
| API                                  | Application Programming Interface |
| RPC                                  | Remote Procedure Call |
|AWS					|Amazon Web Services|
|GCP					|Google Cloud Platform|

<a name="sp1.3"></a>

### 1.3 References 
[1] gNMI Specification: https://github.com/openconfig/reference/blob/master/rpc/gnmi/gnmi-specification.md  
	[2] gRPC Documentation: https://grpc.io/docs/
<a name="p2"></a>

## 2. System Description
<a name="sp2.15"></a>

### 2.1 Context and Motivation
This project aims to address the need for an efficient and scalable way to manage network devices by developing a micro-service using Java/Groovy and the gNMI/gRPC framework. The goal is to simplify network device management, provide periodic updates for operational data retrieval, and offer an easy-to-use interface. 

### 2.2 Project Obectives 
The main objectives of the project are to develop a micro-service with two interfaces (northbound and southbound) for the efficient management of network devices and to support operational data retrieval with periodic updates. the service should be able to:
- Identify the gNMI/gRPC Java/Groovy implementation
- Define input and output APIs for the microservice based on Open-API
- Implement the gNMI/gRPC client and wrap it into a microservice based on SpringBoot 
- Build a test gNMI server to validate the client
- Test the gNMI primitives using the test environment
<a name="p3"></a>

## 3. Requirements

| Priorità | Significato | 
| --------------- | ----------- | 
| M | **Mandatory**   |
| D | **Desiderable** |
| O | **Optional**    |
| E | **future Enhancement** |

<a name="sp3.1"></a>
### 3.1 Stakeholders
1. Network administrators
2. Developers
3. Network device manufacturers
4. Network users (it is probably another machine/service who needs to communicate with another node using GNMI)

<a name="sp3.2"></a>
### 3.2 Functional Requirements 

| ID | Descrizione | Priorità |
| --------------- | ----------- | ---------- | 
|1.0|	The microservice must have a northbound and a southbound interface. |M|
|2.0|	The northbound interface must be able to receive requests from users. |M|
|3.0|	The northbound interface must include a field for identifying the intended network element for each request.|M|
|4.0|	The northbound interface must manage subscriptions from users.|M|
|5.0|	The northbound interface must automatically receive updates on configuration changes.|M|
|6.0|	The northbound interface must define input and output APIs for the microservice using Open-API.|M|
|7.0|	The southbound interface must forward and translate requests to their intended nodes.|M|
|8.0|	The northbound and southbound interface should support the following RPCs: Set, Get, Subscribe, and Capabilities.|M|
|9.0|	The Subscribe RPC in northbound interface should allow for streaming (continuous updates), polling (periodic updates), and once (single update) subscription types.|M|
|10.0|	Users should be able to initiate on-demand configuration polling.|M|
|11.0|	Network administrators have the ability to install, manipulate, and configure network devices through third parties|M|
|12.0|	The service should be able to view operational data from network devices.|M|

<a name="sp3.3"></a>
### 3.2 Non-Functional Requirements 
 
| ID | Descrizione | Priorità |
| --------------- | ----------- | ---------- | 
| 1.0 | Implemented using Java/Groovy and the gNMI/gRPC framework |M|
| 2.0 | Developed using the Spring Boot framework  |M|
| 3.0 | Authentication, authorization, and access control for nodes  |M|
| 4.0 | Cloud-based platform compatibility with AWS |O|
| 5.0 | Cloud-based platform compatibility with GCP |O|
| 6.0 | The service should be performant in terms of time. The transition time through this service should be a matter of milliseconds.|M|
| 7.0 | The service should be performant in terms of space. The memory footprints should be optimized.|M|
| 8.0 | The service should be able to handle 100 requests per second for a node.|M|
| 9.0 | The service should be stateless and scalable design. the service should be replicable without a problem.|M|
