# Innovative Management Stack

## Design Requirement Specification Document

DIBRIS – Università di Genova. Scuola Politecnica, Corso di Ingegneria del Software 80154


<div align='right'> <b> Authors </b> <br> MACIEJ BOGDALSKI <br> SOODEH BALANI  </div>

### REVISION HISTORY

Version | Data | Author(s)| Notes
---------|------|--------|------
1 | 27/04/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | First Version of the document. Document Template
2 | 04/05/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Revised structure of the project
3 | 18/05/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Changes made in the architecture 

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
![System Architecture](https://www.plantuml.com/plantuml/png/fO-nJiCm48RtUufJz_G1EbILOEb05yHKTRZ41qIDxoBxJ8KGxuv5h81acONL___zd-yGZO8bFm7CD5pfXN5pqc2llIPnUAT-Lc6n3Nu0uladMfCBVFvY7pMrs19Lh2EZdcA6x_bj71WVIF9MADZoMDw-WVE9xQRXYaiKBzWvlKctR-bt-1GvGhNVfJb3ZNjtfUUmVw_GrLZzPUKYm2Z6uPaoToorfPKjFiaua8qgqF1lj_k_iEmv-dbYdyiHok7_se03Yqj-WYy0)


### <a name="interfaces"></a>  3.2 System Interfaces and Components
In this section, we describe the primary points of interaction (interfaces) within the system, as well as key components of the system architecture. The key interfaces and components are:

| Component  | Description |
| ------------- | ------------- |
| Northbound Component | This component serves as the interface for external clients. It receives and processes the requests from clients, translates them into device-specific operations, and forwards these operations to the southbound component for execution. |
| Southbound Component  | This component serves as the interface for the network devices. It takes device-specific operations from the northbound component, translates them into device-communicable commands, and interacts with the devices for command execution and response collection. |
| DeviceManager Component  | This component acts as the device catalog for the network. It is responsible for managing the device entries and provides the necessary information to the northbound and southbound components for device-specific operation translation and execution.  |
| INetworkDeviceCommunication | This is the communication interface implemented by both the Northbound and Southbound components. It contains the methods that enable communication between these two components for carrying out operations on the network devices. |
| Network Devices | These represent the physical devices in the network. They communicate with the Southbound Component to receive commands and return responses. |





### <a name="data"></a>  3.3 System Data
This section provides an overview of the system's inputs and outputs.

#### <a name="inputs"></a>  3.3.1 System Inputs
The system inputs for the proposed architecture involve interactions both from the Northbound Component and the Southbound Component:

- **Northbound Component:**
The inputs from the Northbound Component primarily originate from external clients or applications that require interactions with the network devices. Here are the different types of inputs for this component:

| Input Type                   | Description                                                                                                                                                 |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Configuration Requests       | These requests from external clients may be for getting or setting the configuration of a specific device. The request usually includes the device ID and, for setting operation, the new configuration data. |
| Device Management Requests   | These requests involve adding or removing devices from the system. These requests contain the necessary information about the device such as device ID, device type, and perhaps some initial configuration data. |
| Subscription Requests        | These requests involve the creation, updating, or deletion of subscriptions. Such requests would include the device ID, subscription type, and any other necessary parameters such as the desired polling interval for a periodic polling subscription type. |
| Query Requests               | These requests involve obtaining information about the state of the system, the devices, or active subscriptions. For instance, a client might request a list of all currently connected devices, or the current configuration of a particular device. |
| Command Requests             | These are more direct command requests from external clients, such as a command to reboot a particular device, or to execute some other device-specific operation. |
| Authentication Data          | If the system includes any kind of access control or user authentication, then the Northbound Interface would also need to accept inputs related to that, such as usernames, passwords, tokens, etc. |  

 &nbsp;
 

  - **Southbound Component:**
The Southbound Component inputs are mostly device responses to requests or device-initiated updates. These inputs generally include the following:

| Input Type                   | Description                                                                                                                                                 |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Device Responses       | Devices respond to GET and SET requests from the Northbound Interface. The response could be a status message indicating the success or failure of the operation, or the requested configuration data in the case of a GET request. |
| Device Updates   | Network devices may autonomously send updates or notifications. These could include changes in the device's status or configuration, alerts about exceptional conditions, or other types of unsolicited messages. |

&nbsp;


#### <a name="outputs"></a>  3.3.2 System Ouputs

The outputs from the proposed architecture mainly go to external clients or applications and network devices, stemming from both the Northbound and Southbound Components:

  - **Northbound Component**

The following table shows the types of outputs from the Northbound Component:

| Output Type | Description |
|-------------|-------------|
| Configuration Response | This is a response to a configuration request and it would include the requested configuration data or the status of the setting operation. |
| Device Management Response | This would be a response to a device management request and would include the status of the requested operation such as success or failure message. |
| Subscription Response | This is a response to a subscription request and it could include the status of the subscription operation or the data being reported by a subscription. |
| Query Response | This would be a response to a query request and it could include the requested information such as the list of connected devices or the current configuration of a specific device. |
| Command Response | This would be a response to a command request and it would include the status of the command operation such as success or failure message. |
| Authentication Response | If the system includes any kind of access control or user authentication, then the Northbound Interface would also provide outputs related to that, such as authentication success or failure messages, tokens, etc. |

&nbsp;

  - **Southbound Component**

The following table shows the types of outputs from the Southbound Component:

| Output Type | Description |
|-------------|-------------|
| Device Requests | These are the GET and SET requests sent to the network devices. The request would include the device ID and, for a SET request, the configuration data to be set. |
| Device Management Commands | These are commands sent to the network devices to add or remove them from the system. |
| Subscription Commands | These are commands related to the creation, updating, or deletion of subscriptions. They would include the necessary parameters such as the device ID, subscription type, and any other necessary parameters. |
| Responses to Device Updates | When a network device autonomously sends an update or notification, the Southbound Interface may respond with an acknowledgement or with a command triggered by the update. |

&nbsp;

## <a name="sys-module-1"></a>  4 System Module 1

### <a name="sd"></a>  4.1 Structural Diagrams
This subsection presents structural diagrams for the system module, including class and object diagrams to provide a comprehensive understanding of the system's structure and component organization.

#### <a name="cd"></a>  4.1.1 Class Diagram
![Class Diagram](https://www.plantuml.com/plantuml/png/fLN1Rjim3BtxAmIVacuEjgjH51tQIu2jXsK_e4Yi8yoMCb5E3zl-VMBe2BQc1YXwYGJnyV4zYdBkCKWVng6lAjLBHF7aV3ZitMZrWnkEpe8DudSbn8UZTmeGlyEl4J2iFA-jc0xMjCEOW41evK46U8IJKR3IvsLhdrqh7jC-PfoSqOJMq4EErgtO1MziDyDr41ZqrKG9CK3hBH_TLNyl7dPklEg14xx5dUm0_oluKGmSQiM4cKb0i7go_CCnKO6Wq1qY88boZy5B5GHhdRH8Aplmh59glI1knHE45-T_ykbCZ8V1dTxGjm8e8r-WCRKuOLAdZGtWdwK2iLq4OyU7qHebWt4s-c6-Gekq33AEG_pDHWVV1kT9surxLDuSgMeX6sTXdiDvGZQDIbe5_O9naPztE0tUifLdyqsk9JMOZrVh54WibqPQ2ueAfk6OsGN4abDYuz0PspjCKAJrgle9-Qhk9gD8PgR4vEQgpVI6oj-HnNGhCAVBEoMAQKkIb-_cza-ZtOjjYf-lLcpj0Rm9TAzPOlsv5gxnfZk4HjIVDfkREhl5LdpfEWyT3_IA4t5TbIeHMzFGyjeb4XsYK-PCLnLHIHPRgc_gT3MjE1T3ms8UQ1X8p2MU16Gkih8bxPlDtRMdJ5qSZZqC4OlLpetl8wZkmMhws_a7)

##### <a name="cd-description"></a>  4.1.1.1 Class Description
| Class | Description |
|-------|-------------|
| NorthboundComponent | Handles the requests coming from the Northbound interface. It processes the requests and sends them to the appropriate classes for further processing. |
| SouthboundComponent | Responsible for handling and processing device messages. It communicates with the devices using the `Session` instances created by the `DeviceConnectionManager`. |
| INetworkDeviceCommunication | This is an interface that defines the methods for communication with the network devices. It is implemented by the `DeviceManager` class. |
| DeviceManager | Handles the addition, removal, and retrieval of `NetworkDevice` instances. It maintains a map of device identifiers to their respective `NetworkDevice` instances. |
| NetworkDevice | Represents a network device in the system. Each device has a unique identifier. |
| DeviceConnectionManager | Manages the creation and retrieval of `Session` instances for each network device. |
| Session | Represents a connection to a network device. Each session allows sending requests to a device and receiving messages from it. |


#### <a name="od"></a>  4.1.2 Object Diagram
![Object diagram](https://www.plantuml.com/plantuml/png/VP8nRuGm38Nt_0hhzXkuyNH29y7IeLFNBY6uaAgngyHf_tv3MYH2mstulSyFsyezYFOItOVYuHsD0B6NUU18OyVkamb9LYjmFB96_B86dpNf2NqfTaoKAikqimE6aDIcn7VbHQdAH70-j-K_uGgtMqoHRQiAOuCs6NAB_f2xbDpX09LxRCT8ThMjVfPoWpgIszUpPTvur99RLldVleQTYsJDmi6tbJd1YbWGl9rc0NxRHhxyVi9JVuK6NgdFIRay_QMUbBeZZSkR-G40)

### <a name="dm"></a>  4.2 Dynamic Models
The dynamic models section presents a series of sequence diagrams that depict the interactions between the system components during various operations, emphasizing the behavior and collaboration of objects over time.

#### <a name="rdc"></a> 4.2.1 Retrieving device configuration
![Retrieve Device Config](https://www.plantuml.com/plantuml/png/XP8nJiKm38Ptdo9Zt_0km82UL1O66E04eR5AfTQ1sEtvSN6ZggBcJOui_r--ABwnn2BpD4Bi99VGZWEI05WDryVmcejyVkIPKfkdhqnh-o4mKdh3xnbPccBriYA69GgUPG3EGCf_mcNey2LIxB4ekKUnLfD-otFQmSTXW6FswjeQ2H9we7_Tsao46ioqMtO55VEEpDfsJVz60JocRyzsIzNtwVv3Dj5x7rVd06h0joh8UcOqhKFSqL2KhLNLs0XDHLsCvU_GJLFhhlu0)
1. The Client sends a `getConfiguration()` request to the NorthboundInterface.
2. The NorthboundInterface retrieves the device (identified by the provided deviceId) from the DeviceManager.
3. The NorthboundInterface then asks the DeviceConnectionManager to create a new Session for the device.
4. The DeviceConnectionManager returns the newly created Session.
5. The NorthboundInterface sends a request to the Session to get the device configuration.
6. The Session communicates with the NetworkDevice to retrieve the configuration.
7. Once the Session has the configuration, it sends it back to the NorthboundInterface.
8. The NorthboundInterface then sends the configuration back to the Client.

This process enables the system to retrieve device configurations dynamically based on the Client's request, ensuring that the Client always has access to up-to-date device information.


#### <a name="udc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/png/TPEnSiCW38Ptde8nlKjUe4ClLxnuY8VG5w2Wf5mJI45ENxy8NDVgnOCNyUcN_b_dVKQJSBnUXB4OarGN3m75hHIzzJSJK0unuSTx78Fh0q8w6GlIP3cyzWphuEuj74mmPqW4T8S7xog601Pz34jISLH3peMW9yqVulXe5VrlbNu0_8xfytVazJe6RmrEcltGASOpl7hhX6X9oDqBEPNFCWCEqP61ap-FgQfjGgdqRYljilf4MVgxGQWXbOzKkaCHEGEsALFd0YrnYSBive6kWiB10fd55AdP1AKyfRLfFDTK9AgMghdHJ5RDkheImHtXQuICcmp834_rnGWjXAwTHO3rliNcT4rZAnyiJ9VKRHMwKejzThQUCy52r1PoshL6VglL6tMYDEp-HXuXtsB8BFe2JVBDwmf6I3D068UOIQ7dcwn0OcWoqo-vjXTfxI4u-eL_0000)
1. The Client sends a `setConfiguration()` request to the NorthboundInterface, which includes the new configuration.
2. The NorthboundInterface retrieves the device (identified by the provided deviceId) from the DeviceManager.
3. The NorthboundInterface then asks the DeviceConnectionManager to create a new Session for the device.
4. The DeviceConnectionManager returns the newly created Session.
5. The NorthboundInterface sends a request to the Session to update the device configuration.
6. The Session communicates with the NetworkDevice to apply the new configuration.
7. Once the Session confirms that the configuration update is complete, it sends a confirmation back to the NorthboundInterface.
8. The NorthboundInterface then sends a confirmation back to the Client.

This process allows the system to dynamically update device configurations based on the Client's request. This ensures that device configurations can be updated as needed without manual intervention.


#### <a name="sm"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/png/bLJ1hfim4Bpp5Qjtuad93rHwLLHpuH0EeJ_Ws9lKAjZKDkdlrykcba6arRlamCpEp8vNEOKeVPp7eP8gEWzYC6XZDQMFHfb9sWYTy_77rSrMjpQYlqc580DqtzeLhS67KNYMLjxH4w4vx-32MOig6cTBfbXJUmmXCGZgru2RzwBqcoXjX_6typ__MexZR8sIaJKJt8YguatX-9KsWIymBddw-HgKDnDnQpUXNwOEO9t6LXyW57XhR-wTUZCF6N4fH0uHyXHyVC2RyfZ0jme89xVcdCpk63bSpSBl2Sz2gRWAY7VCuVH2Jbyr5hH2L90gUptRgtbcgorON9JgWISsygBGxnUMrHVyDME8DJkLPJsHbLbFLlqoJpwbmdV7-xzMEi3U0gH4eXoBNfBttcZmylilLghnAG0B7NFG2uR9sR0fd4ZipTGzOiv8n5pnIo8tlF3u7lUe-Rfm2BWvBuq3bkULAv5MuR2gP5M4_dSDtNzg-6HkgwjJ-g6_cJy0)


1. The `Client` sends a subscription management request to the `NorthboundInterface`. The request includes the operation to be performed (create or delete), the device ID, and, in the case of creation, the subscription information.

2. If the operation is "create":

   - The `NorthboundInterface` calls the `getDevice()` method of the `DeviceManager` to fetch the device details using the provided device ID.
   - The `DeviceManager` returns the `NetworkDevice` object corresponding to the device ID back to the `NorthboundInterface`.
   - The `NorthboundInterface` then requests the `DeviceConnectionManager` to create a new session with the device.
   - Once the `DeviceConnectionManager` returns the newly created `Session`, the `NorthboundInterface` uses the session to send a create subscription request.
   - The `Session` passes the create subscription request to the `SouthboundInterface` via the `sendRequestToDevice()` method.
   - The `SouthboundInterface` calls the `createSubscription()` method of the `INetworkDeviceCommunication` interface to create the subscription with the provided details.
   - Once the subscription is successfully created, a response is sent back up the chain to the `NorthboundInterface`, which is finally sent back to the `Client`.

3. If the operation is "delete":

   - The `NorthboundInterface` calls the `cancelSubscription()` method of the `INetworkDeviceCommunication` interface to cancel the subscription with the provided subscription ID.
   - A response confirming the cancellation of the subscription is sent back up to the `NorthboundInterface` and is finally sent back to the `Client`.


#### <a name="dcm"></a> 4.2.4 Device connection management (connect, disconnect)
![Device Connection Management](https://www.plantuml.com/plantuml/png/dLFDJiCm3BxdAQoTEecz0DBGf77f0Gxm15vYjeZKAOaBhq-wLAL1ZH2xL9NjxqVVfnoYO92XSmgr-017Pub5zMbejUsH1PvzaCl93snQ5WflgAdOFzAdrVI4Z6SAAvkZPoOjrlFQpIl5c5Qb8icN3-yPhbJs1Bk7DIjm3zr8kv1fV4y1nxyxC5UErcpL2ZPHbkuJsPaaZveVQ7cpgnh928LEOBO0-priTFQsKG3hDcfH9L8T28Mcc1Qce8gf6uoPfMvZahcUidcXZu6YD7gMc2P9VJvDgAAlX4qNU78sNXQTR1MvIBzoCJOMqTpS4C3_Ehgf2EVZtCEKP6l-Y48eT9PHg8o2ZJgap_ZklW40)

1. The Client sends a request to manage a device connection (either "connect" or "disconnect") to the NorthboundInterface.
2. The NorthboundInterface asks the DeviceManager to get the NetworkDevice associated with the provided deviceId.
3. The DeviceManager returns the NetworkDevice to the NorthboundInterface.

If the operation is to "connect":

4. The NorthboundInterface requests the DeviceConnectionManager to create a new Session for the device.
5. The DeviceConnectionManager returns the newly created Session.
6. The NorthboundInterface sends a connection request to the Session.
7. The Session communicates with the NetworkDevice to establish the connection.

If the operation is to "disconnect":

4. The NorthboundInterface asks the DeviceManager to get the NetworkDevice associated with the provided deviceId (again).
5. The DeviceManager returns the NetworkDevice to the NorthboundInterface.
6. The NorthboundInterface requests the DeviceConnectionManager to close the Session associated with the deviceId.
7. The Session communicates with the NetworkDevice to terminate the connection. 

This process shows how the connection management for devices is handled in the system. The NorthboundInterface coordinates with other components, such as DeviceManager and DeviceConnectionManager, to handle the requests from the Client. The actual communication with devices is handled through Sessions, which manage the lifecycle of the connection and the communication to the NetworkDevices.

