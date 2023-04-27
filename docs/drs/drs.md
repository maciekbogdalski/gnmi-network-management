# Title of the project

## Design Requirement Specification Document

DIBRIS – Università di Genova. Scuola Politecnica, Corso di Ingegneria del Software 80154


<div align='right'> <b> Authors </b> <br> MACIEJ BOGDALSKI <br> SOODEH BALANI  </div>

### REVISION HISTORY

Version | Data | Author(s)| Notes
---------|------|--------|------
1 | 27/04/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | First Version of the document. Document Template

## Table of Content

1. [Introduction](#intro)
    1. [Purpose and Scope](#purpose)  
    2. [Definitions](#def)
    3. [Document Overview](#overview)
    4. [Bibliography](#biblio)
2. [Project Description](#description)
    1. [Project Introduction](#project-intro)
    2. [Technologies used](#tech)
    3. [Assumptions and Constraints](#constraints)
3. [System Overview](#system-overview)
    1. [System Architecture](#architecture)
    2. [System Interfaces](#interfaces)
    3. [System Data](#data)
        1. [System Inputs](#inputs)
        2. [System Outputs](#outputs)
4. [System Module 1](#sys-module-1)
    1. [Structural Diagrams](#sd)
        1. [Class Diagram](#cd)
            1. [Class Description](#cd-description)
        2. [Object Diagram](#od)
        3. [Dynamic Models](#dm)

##  <a name="intro"></a>  1 Introduction
The design specification document reflects the design and provides directions to the builders and coders of the product.
    
### <a name="purpose"></a> 1.1 Purpose and Scope
This document provides a detailed design specification for the micro-service implementing a Java/Groovy gNMI/gRPC client to manage network devices. The intended audience includes developers, project managers, and stakeholders.


### <a name="def"></a> 1.2 Definitions
| Acronym  | Definition |
| ------------- | ------------- |
| gNMI  | gRPC Network Management Interface  |
| gRPC  | gRPC Remote Procedure Call  |
| URS  | User Requirements Specification  |   


### <a name="overview"></a> 1.3 Document Overview
This document is organized into sections describing the project, system overview, and a system module covering Northbound and Southbound interfaces.

### <a name="biblio"></a> 1.4 Bibliography
[1] User Requirements Specification (URS) for the gNMI/gRPC Client Micro-service


## <a name="description"></a> 2 Project Description

### <a name="project-intro"></a> 2.1 Project Introduction 
The project aims to deploy a micro-service that allows users to install, manipulate, and delete configuration of network devices and view operational data.

### <a name="tech"></a> 2.2 Technologies used
The micro-service will be implemented using Java/Groovy, gNMI/gRPC framework, and Spring Boot framework.

### <a name="constraints"></a> 2.3 Assumption and Constraint 
The micro-service must adhere to the mandatory constraints and requirements specified in the URS.

## <a name="system-overview"></a>  3 System Overview
The system comprises a micro-service that communicates with network devices through Northbound and Southbound interfaces.

### <a name="architecture"></a>  3.1 System Architecture
The micro-service has a modular architecture with two main components: Northbound Interface and Southbound Interface.
![System Architecture](https://www.plantuml.com/plantuml/dpng/SoWkIImgAStDuU8gI4pEJanFLL3opKmkoYzEBIhBJ4vLKgZcKb28ziilAib8oY_DIr7mp2j9BKfBJ4wD1Ka4vvTYaQZbuehsIoqfpo_ALd191PbJ7AkWcv9VdgThXqiFTZiGkaA1BYgEvU9oICrB0RaN0000)


### <a name="interfaces"></a>  3.2 System Interfaces
| Interface  | Description |
| ------------- | ------------- |
| Northbound Interface  | This interface is responsible for receiving requests and managing subscriptions from external clients. It defines the input and output APIs for the microservice using Open-API and includes a field for identifying the intended network element for each request  |
| Southbound Interface  | This interface forwards or translates requests to their intended network devices, using a unique identifier provided by the northbound interface to ensure proper delivery  |



### <a name="data"></a>  3.3 System Data

#### <a name="inputs"></a>  3.3.1 System Inputs
Requests from users, including configuration changes, viewing operational data, and subscription management.

#### <a name="outputs"></a>  3.3.2 System Ouputs
Responses to configuration requests (installation, manipulation, and deletion) from network devices.
Responses to operational data requests from network devices.
Notifications about changes in operational data based on the subscription types (stream, poll, once) for the subscribed clients.

## <a name="sys-module-1"></a>  4 System Module 1

### <a name="sd"></a>  4.1 Structural Diagrams
This subsection presents structural diagrams for the system module, including class and object diagrams to provide a comprehensive understanding of the system's structure and component organization.

#### <a name="cd"></a>  4.1.1 Class Diagram
![Class Diagram](https://www.plantuml.com/plantuml/dpng/VLAzQeOm5Dtp525JVr3QjPFGB5rSx0kaIIgXcaXoOocbxzuFhs0k1ZSvFuTphkaIg0XvdfYUL4gyj_0TufUqgzEM_pBE6xDzlyaNFa1qVZomNitsZBx_B2NwE5gGUyJTVON0d2g5OPHAnEMB2ZVP6fn1KUa7R4H4VsnV_1hyfnjpLE22nyLwW-XYnAG2HNi2-icsWq07fHAX32RMkmtv8-debgDQAX1Ia32LNr16aR9dkdfp8hWuAdr0ubdmfk7YgMqV13trbP8CPvtr9i_JFm00)

##### <a name="cd-description"></a>  4.1.1.1 Class Description
| Class  | Description |
| ------------- | ------------- |
| NorthboundInterface  | This class is responsible for receiving requests and managing subscriptions from external users or systems. It includes methods for handling the different types of requests, such as set(), get(), subscribe(), and capabilities().  |
| SouthboundInterface  | This class is responsible for forwarding or translating requests to the intended network devices. It also includes methods for handling the different types of requests, such as set(), get(), subscribe(), and capabilities()  |
| NetworkDevice  | This class represents a network device in the system. It stores the device's unique identifier and other relevant information  |

#### <a name="od"></a>  4.1.2 Object Diagram
![Object diagram](https://www.plantuml.com/plantuml/dpng/jP4zReD048LxJh5beiWNSEL8D3HH54umm416MMRm_DX5bBj7o382cTRbV6zttwUTapcenvYBDeDP-a2_YlwKUA4Mqs-HqbimdGEhyfXgL-9XPgrmJqCeE0cl0vdm1Y6NuB14VyMzGDI_6mdkAdRK7kurn5Fu-sUrUIty4Ox0C60TZRLAqzprydfC5w5kJWrzFGxRqMwt6bp35YPeA9CJsi8NuLfYNxWNlO9sNtWEj4sBAx1bS5oof_ZjVUNXi5rXiQV_SEY4tCMO_m40)

#### <a name="dm"></a>  4.2 Dynamic Models
![Dynamic Models](https://www.plantuml.com/plantuml/dpng/fOynReGm44LxdsAqVIv04UqcAD8s544vm6263LgmoSmuYDi71u6ioAcsjFJy_hopABA6SJ1erJDy2B5fV70jyWAdohD-_ZxXwfIuGqidG87gvPfWjGzvh5wn5XKR55fjfBFdEvJqtU-UqfYu2K_FqLd0Etq54WNhNTVV0gFsta6OLWkPbOXW7S4tppDocmM1DukfjmzLMS1bceRb7srvM2_sxlmyK7kZtIN1MX8vNEau7lX8JlVxOb01bTU-M_vQyhKI6h7SJ_6gR6KAvEfUcRQLr9FFIfb7uxAkCxasZCCF)
