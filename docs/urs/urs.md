
### User Requirements Specification Document
##### DIBRIS – Università di Genova. Scuola Politecnica, Software Engineering Course 80154


**VERSION : 3.0**

**Authors**  
Maciej Bogdalski
Soodeh Bakrani

**REVISION HISTORY**

| Version    | Date        | Authors      | Notes        |
| ----------- | ----------- | ----------- | ----------- |
| 1.0 | 2023-03-29 | Maciej Bogdalski, Soodeh Bakrani | First draft |
| 2.0 | 2023-04-06 | Maciej Bogdalski, Soodeh Bakrani | Content accepted by the client after corrections |
| 3.0 | 2023-04-17 | Maciej Bogdalski, Soodeh Bakrani | Changed layout |

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

<a name="sp1.3"></a>

### 1.3 References 
[1] gNMI Specification: https://github.com/openconfig/reference/blob/master/rpc/gnmi/gnmi-specification.md  
	[2] gRPC Documentation: https://grpc.io/docs/
<a name="p2"></a>

## 2. System Description
<a name="sp2.15"></a>

### 2.1 Context and Motivation
The micro-service aims to enable efficient and scalable management of network devices using Java/Groovy and the gNMI/gRPC framework. 
<a name="sp2.2"></a>

### 2.2 Project Obectives 
The main objectives of the project are to develop a micro-service with two interfaces (northbound and southbound) for the efficient management of network devices and to support operational data retrieval with periodic updates. 
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
4. End-users 

<a name="sp3.2"></a>
### 3.2 Functional Requirements 

| ID | Descrizione | Priorità |
| --------------- | ----------- | ---------- | 
| 1.0 |  Two interfaces: Northbound (managing requests, subscriptions, and APIs) and Southbound (forwarding and translating requests). RPCs for both interfaces include Set, Get, Subscribe (stream, poll, once), and Capabilities. |M|
| 2.0 |  Install, manipulate, and delete configuration of network devices |M|
| 3.0 |  View operational data from network devices |M|
| 4.0 |  Support periodic updates for operational data |M|
| 5.0 |  Stateless, scalable, and performant micro-service design |M|

<a name="sp3.3"></a>
### 3.2 Non-Functional Requirements 
 
| ID | Descrizione | Priorità |
| --------------- | ----------- | ---------- | 
| 1.0 | Implemented using Java/Groovy and the gNMI/gRPC framework |M|
| 2.0 | Developed using the Spring Boot framework  |M|
| 3.0 | Clear and complete documentation  |M|
| 4.0 | Authentication, authorization, and access control  |O|
| 5.0 | Cloud-based platform compatibility (e.g., AWS, GCP) |O|
