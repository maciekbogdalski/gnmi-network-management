# Innovative Management Stack

## Design Requirement Specification Document

DIBRIS – Università di Genova. Scuola Politecnica, Corso di Ingegneria del Software 80154


<div align='right'> <b> Authors </b> <br> MACIEJ BOGDALSKI <br> SOODEH BALANI  </div>

### REVISION HISTORY

Version | Data | Author(s)| Notes
---------|------|--------|------
1 | 27/04/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | First Version of the document. Document Template
2 | 04/05/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Revised structure of the project

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
            1. [Retrieving device configuration](#rdc-description)
            2. [Updating device configuration](#udc-description)
            3. [Subscription management (create, update, delete)](#sm-description)
            4. [Device connection management (connect, disconnect)](#dcm-description)

##  <a name="intro"></a>  1 Introduction
The design specification document reflects the design and provides directions to the builders and coders of the product.
    
### <a name="purpose"></a> 1.1 Purpose and Scope
This document provides a detailed design specification for the micro-service implementing a Java/Groovy gNMI/gRPC client to manage network devices using northbound and southbound interfaces. The intended audience includes developers, project managers, and stakeholders.

### <a name="def"></a> 1.2 Definitions
| Acronym  | Definition |
| ------------- | ------------- |
| gNMI  | gRPC Network Management Interface  |
| gRPC  | gRPC Remote Procedure Call  |
| URS  | User Requirements Specification  |
| DRS  | Design Requirements Specification  | 

### <a name="overview"></a> 1.3 Document Overview
This document is organized into sections describing the project, system overview, and a system module covering Northbound and Southbound interfaces and Device Management.

### <a name="biblio"></a> 1.4 Bibliography
[1] User Requirements Specification (URS) for the gNMI/gRPC Client Micro-service


## <a name="description"></a> 2 Project Description

### <a name="project-intro"></a> 2.1 Project Introduction 
The project, Innovative Management Stack Using gRPC and OpenAPI, aims to deploy a micro-service that allows users to install, manipulate, and delete configuration of network devices and view operational data through northbound and southbound interfaces.

### <a name="tech"></a> 2.2 Technologies used
The micro-service will be implemented using Java/Groovy, gNMI/gRPC framework, OpenAPI, and Spring Boot framework.

### <a name="constraints"></a> 2.3 Assumption and Constraint 
The micro-service must adhere to the mandatory constraints and requirements specified in the URS.

## <a name="system-overview"></a>  3 System Overview
The system comprises a microservice that communicates with network devices through Northbound and Southbound interfaces, as well as managing the devices themselves through the Device Management Interface.

### <a name="architecture"></a>  3.1 System Architecture
The micro-service has a modular architecture with two main components: Northbound Interface and Southbound Interface.
![System Architecture](https://www.plantuml.com/plantuml/dpng/XL9DIyGm4BtdLmozxs7t1yZAwg47Yz2Zz31DHWsDav9CL13_kwcAf2dIu_jum-CnvoWO9BquDQEUy8cWwNoGvz4dDj2oK7X4JGryA827s_UC6HrSd2MME2nauR--RmVrgPH1mH4ZGTFxzD-nBDKvxNmokG79cmyJtD2hrHH__Jy8ZaC1JYNelcziIyBXSBMjMHEBw-_K7NBU8cHNHQnS5yzCMgpdEb1hHRPFOzJ1pklOVgwUgoGs_Oyx-cb7prEgCx5P7kCB)


### <a name="interfaces"></a>  3.2 System Interfaces
| Interface  | Description |
| ------------- | ------------- |
| Northbound Interface  | This interface is responsible for receiving requests and managing subscriptions from external clients. It defines the input and output APIs for the microservice using Open-API and includes a field for identifying the intended network element for each request.  |
| Southbound Interface  | This interface forwards or translates requests to their intended network devices, using a unique identifier provided by the northbound interface to ensure proper delivery.  |
| INetworkDeviceCommunication  | This interface facilitates communication between the Northbound and Southbound Interfaces. It contains the necessary methods for exchanging data between the two interfaces.  |


### <a name="data"></a>  3.3 System Data
This section provides an overview of the system's inputs and outputs.

#### <a name="inputs"></a>  3.3.1 System Inputs
The system inputs include:

- Configuration requests from clients, which may consist of installation, manipulation, and deletion of network device configurations.
- Subscription requests for updates on specific network devices.
- Requests for information about network devices' capabilities.

#### <a name="outputs"></a>  3.3.2 System Ouputs
The system outputs include:

- Responses to configuration requests, including success or failure messages and any relevant information.
- Data updates for subscribed clients, which may be streaming (continuous updates), polling (periodic updates), or once (single update) subscription types.
- Information about network devices' capabilities upon request.


## <a name="sys-module-1"></a>  4 System Module 1

### <a name="sd"></a>  4.1 Structural Diagrams
This subsection presents structural diagrams for the system module, including class and object diagrams to provide a comprehensive understanding of the system's structure and component organization.

#### <a name="cd"></a>  4.1.1 Class Diagram
![Class Diagram](https://www.plantuml.com/plantuml/dpng/fLLFxneX4Btlfo2UjEgXrqtJsDYB1prKlm32k9BkmfO_DatJxzvbmSfGr9_vdHPdvZtUF0PS6KkrTLrRiPOQGpOxi3-L_luLBeB1MdMTau9HAvGaloj2vWtOdU93Hfv4u_IOcSeXik4rsLijP3Eh2Kfxc7aEMn2MPZEIWVIY1FTS7PMqWRqx6gP5NsGoIVBmguSXaqKMX3A_nfalBEmLYMB3QzhJesY55M0AlIRPwayLZKG-ZjOjkLyds5iiICHx8gVAbcP5Rx0lqQUqFH-LatmZBUWJZIhdPofv2z_WXmDZfpfyQn83CxyolP86RbnxvSfS1YIFm8CALlp7k22vByKzhWCf9J3Vq7OS0XsSPV_2fYPRsd-wpa6E-knLnUffsBV6HvNC6HS6bMA9iIgULUGl5UOQ4j-IoKFzfEEBEqAOq8q6Qjzx6sOuv7bTptFUbESbVkGgjF2O5tkSZm0oAjYDBSBdENf4EG-_dfwvXavT81O_FlNxHOZSwozTjid72Lak_UVXeriJPy1K-HLxdU7U1V9mvMa-JB97goRXfRrYNq4KHZlLUqzCrBe2oVr_qby0)

##### <a name="cd-description"></a>  4.1.1.1 Class Description
| Class  | Description |
| ------------- | ------------- |
| INetworkDeviceCommunication  | An interface that defines the required methods for communication between the Northbound and Southbound Interfaces. It facilitates data exchange and ensures proper interaction between the two interfaces.  |
| NetworkDevice  | A class representing network devices, containing properties such as deviceId and deviceType. It also includes methods for getting and setting device configurations.  |
| NorthboundInterface  | A class responsible for handling requests from external clients. It processes requests and communicates with other components of the system to provide appropriate responses.  |
| DeviceConnectionManager  | A class responsible for managing connections to network devices. It maintains a map of connections and provides methods for connecting, disconnecting, and retrieving connections for specific devices.  |
| SubscriptionManager  | A class responsible for managing subscriptions to network devices. It maintains a map of active subscriptions and provides methods for creating, updating, or deleting subscriptions based on user input.  |
| SouthboundInterface  | A class responsible for forwarding or translating requests to their intended network devices. It uses the unique identifier provided by the Northbound Interface to ensure proper delivery of requests.  |
| DeviceManager  | A class responsible for managing network devices within the system. It maintains a map of devices and provides methods for adding, removing, and retrieving devices based on their unique identifiers. The Northbound Interface uses this class to interact with devices for performing Set/Get operations.  |

#### <a name="od"></a>  4.1.2 Object Diagram
![Object diagram](https://www.plantuml.com/plantuml/dpng/dPA_3i8W48Ttdg8lm617foQwEEWxK7egAeU1GmVZkzkcIgpX93fUFhxxymjD8EKfsfDmxG4qLUWyxLiNiLiXWTyg3TLDtCMB1nVptAJIR82kpX-NS34Q5ixQY4OhCWudhRedHmGzmBL2jGC_7HVReBqvStpiaIFuliICH_M8SdTB-VMMUQLJ3K6aPFxkmEp0vDJRCV1p2nQ7fSnbNhO-yku5Ex0Vd3evXIJO-yjl6y1k-EmF)

### <a name="dm"></a>  4.2 Dynamic Models
The dynamic models section presents a series of sequence diagrams that depict the interactions between the system components during various operations, emphasizing the behavior and collaboration of objects over time.

#### <a name="rdc"></a> 4.2.1 Retrieving device configuration
![Retrieve Device Config](https://www.plantuml.com/plantuml/dpng/bPJ1JiCm38RlVeeSEucz00SqQLnwO0VW1KBYbOhLXZGPhyyoQrGHbYjEaTpyd__yJhERiq-vJ2Vm8NDo-zE8b05aTTi7T-2Ktz-uKEmfOphwWExUpKZn6RyAphbBicugOZpxZ9e6G2bM_YEUnu1Fdlo0wK8UC4kfYzUbZmkutGpGQgjPpD-SFgI-1cis0xJQhM9D611HH_pnewUdWXhbIbPx9iABa6cn5gJM2RbDJ9C0A1-C59TzROlr75TjIyrELPCR-UfObMEa_SB5llclBCZR7t3db5kcK02KOktODoVVypGL6eElPcKC1uxr0CTnAEbQxeXZqyzY05WDzC6ttVupTVimOU5QmxSTwqEow6Ow2ikuP0ddJwOP1MyCkyerhBonlrTAQDqDkwbSwq7T8SNwSFy0)

#### <a name="rdc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/dpng/RP51ZeCm34NtEKMMWpHSO1O8YTcm6ExWIGoDIcraJ7lzWbAfDShs-_ivJsbN1T7jDZi8okAxEI6fM_OmXRG0gHzOzFBF6yMU56M4W6R-Y_SKy0y89fJ2f6CY39gOIfq1zS5opLNdydrVDwMp_iUlg0F7WpccQHCui5-q9ttyzk4phLo9K3VslJjoGitHYrCvsyWhHiGilsCBEFSpolXc8IjpacYHul4zJm00)

#### <a name="rdc"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/dpng/hPF1IiD048Rl-nH3JnNg2mYLGhtqO3te2qnsVtKnsLacauflxzQ8D9906te9WVdt-_OVs4rhhDOrjMDlehIj8vAvp6hHnyp9Q2zgxvLqAUoIGL_POp1_xghMQymM9JrouZVeOFw0G_JOIahmKvazx5Fqeuywrz-2Rk_7v7H7pG_YLBoK3ENZtugI1Ep2YjgJ-SjNneqReHN9M8CYoIKWsZpru7CLN1lz1MczfeLNi67XQCnJz5CBAbVW47xFLD3bj9Ic60K_tEq5KDGj_bNfSfXRfJzw_JOzTswXW1ep2oaQELoxq6LSfE0svNDyljy0)

#### <a name="rdc"></a> 4.2.4 Device connection management (connect, disconnect)
![Device Connection Management](https://www.plantuml.com/plantuml/dpng/fPB1QiCm38RlUWhHKmlR2mmw2jsbX-qTL5jRnHmvoEhs-dEJiSmbEULY3DB__P_1X-9aVkqIKF1iU4p2wj2JkGJfIHtVildbdAyQJ-fixnIucR_mbmG-PbKEBbbVIUc3hMMmVsVx75S1nXPyV9w3unDs0s92RdFFHhVR0yQ1SOexeEJuDy3z7ZTXJ6m0vy2rRqcsTlvcjnCVbxOhg7rHZNDnEYSfbt_ME-1K-CunIbcZMUChJPsj4oNdrbGZ7Efn-me_)
