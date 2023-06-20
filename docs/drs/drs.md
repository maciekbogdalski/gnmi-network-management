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
4 | 31/05/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Modified dynamic models and relations between classes
5 | 07/06/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Revised concept of the session
6 | 15/06/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Two new dynamic models: Adding/Deleting Device and Session creation
7 | 20/06/23 | MACIEJ BOGDALSKI <br> SOODEH BALANI | Correction made to Session Creation 

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
            1. [Retrieving device configuration](#rdc)
            2. [Updating device configuration](#udc)
            3. [Subscription management (create, update, delete)](#sm)
            4. [Add/Delete Device](#add)
            5. [Session Creation](#sc)

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
![System Architecture](https://www.plantuml.com/plantuml/png/XS-nJWCn30RWtKzXTRy-G0TK2POET45CLGVVnS2f5_kKE5G8yUv4N0wGei0Q_v__U1-DWYK_0SmqNEYPSNFKO2-z9d5ugtvMOR4DlWFYwMVqFJd3no_xeAbbowYoZenwYXdVyUiuCDwJv5sXiEMnl7s1qv7jgk62ooYUiNDwbUwcLR_3nyWHglrTcZDgtBihEOVzMqtNnlelgui0enY79yhkK69BAhVu9ED0Dgf0exyq_4NNmnfdRxV_wOEV9_Ov707sB2xvwHC0)


### <a name="interfaces"></a>  3.2 System Interfaces and Components
In this section, we describe the primary points of interaction (interfaces) within the system, as well as key components of the system architecture. The key interfaces and components are:

| Component                 | Description |
| ------------------------- | --------------------------------------------------------------------------------------------------------- |
| NorthboundComponent       | This component serves as the interface for external clients. It receives and processes the requests from clients, and interacts with the DeviceManager for device-related operations. |
| SouthboundComponent       | This component serves as the interface towards the network devices. It takes operations from the NorthboundComponent, translates them into device-communicable commands, and interacts with the DeviceConnectionManager for handling sessions with devices. It also implements the INetworkDeviceCommunication interface for carrying out operations on the network devices. |
| DeviceManager             | This component acts as the device catalog for the network. It is responsible for managing the device entries, and provides the necessary information to the NorthboundComponent for device-specific operation translation and execution. |
| DeviceConnectionManager   | This component is responsible for managing the device sessions. It creates, retrieves, and closes sessions according to the requests from the SouthboundComponent. |
| Session                   | This is a class that represents a session with a network device. It carries out the operations on the NetworkDevice using the INetworkDeviceCommunication interface implemented by the SouthboundComponent. |
| INetworkDeviceCommunication | This is the communication interface implemented by the SouthboundComponent and used by the NorthboundComponent. It contains the methods that enable communication for carrying out operations on the network devices. |
| NetworkDevice             | These represent the physical devices in the network. They are managed by the DeviceManager and communicate with the SouthboundComponent via sessions to receive commands and return responses. |






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
![Class Diagram](https://www.plantuml.com/plantuml/png/bLLDRniX4Btlhq0-fH-xQg-ho4gLN5PgSwZR7q1WOgFkWikmzY7gVo-4YGq4RvIBGSoRDs-U1_O67RTkdiP6Z1oHtHlhTWzcrlBMJ7kZGJlsr33sQM-D0CIVy7S6T5SshZsZWmzXXpu18Q251UxW3WvAm9LyNWRzQ7fsTzhxZ8DHCg0bZ52YPSyspYgzJN1RS17qNNa9FX3MwtYqRlurrCF6p8izn8GVViUtWAyAVcPJ3FMCC8a412sfvLyc4bK8IdU8u2oH973jsMqKnwNC-7fs3-vex9zuccYmC9d3U-ogWJBo38qdPmcJ6Aoq0_l81R0X2tgZfrahmPqocZqHNyKPoHttK-1_Yud1Ty5fm3Rp0mghzg5g9HkJS1o_DA4OGi6rW35ZpF8pXxC_CtFYkVcsr98PF4ZlC0NAaRWrMeC81RFXe7O1iTQfPwCGrNa1Obml8SLe49QmoIHzyw8EB-3pYD31-aGtQ9uZ4xkeKmfTTAfz9ILlY_nRDZQxh9K7AxjF0hnYkko1WhP1StvJhxkk-aJsx3S25k3LrnKphLNRdMlPwalNVLmLizApYCMczcQ5_BODoyNXeBfbE5SWpL5x6b-T34pgqabQgvHuljk0xAZShgavq7NhfJU1b5RxZ1QzJJ1C-n4cdu7D3MWPFbh_0G00)

##### <a name="cd-description"></a>  4.1.1.1 Class Description
| Class | Description |
|-------|-------------|
| NorthboundComponent | Processes requests from external clients and interacts with the DeviceManager and INetworkDeviceCommunication for device operations. |
| SouthboundComponent | Responsible for handling device messages. It communicates with network devices using Session instances provided by the DeviceConnectionManager and implements the INetworkDeviceCommunication interface. |
| DeviceManager | Manages the addition, removal, and retrieval of NetworkDevice instances. It maintains a list of device identifiers and their respective NetworkDevice instances. |
| INetworkDeviceCommunication | Defines the communication methods with network devices. Implemented by the SouthboundComponent class. |
| NetworkDevice | Represents a network device in the system. Each device has a unique identifier. |
| DeviceConnectionManager | Manages the lifecycle of Session instances. It creates, tracks, and destroys Session instances according to a defined policy. |
| Session | Represents an active connection to a network device. It is owned and managed by the DeviceConnectionManager and used by the SouthboundComponent for communication. |




#### <a name="od"></a>  4.1.2 Object Diagram
![Object diagram](https://www.plantuml.com/plantuml/png/VP8nRuGm38Nt_0hhzXkuyNH29y7IeLFNBY6uaAgngyHf_tv3MYH2mstulSyFsyezYFOItOVYuHsD0B6NUU18OyVkamb9LYjmFB96_B86dpNf2NqfTaoKAikqimE6aDIcn7VbHQdAH70-j-K_uGgtMqoHRQiAOuCs6NAB_f2xbDpX09LxRCT8ThMjVfPoWpgIszUpPTvur99RLldVleQTYsJDmi6tbJd1YbWGl9rc0NxRHhxyVi9JVuK6NgdFIRay_QMUbBeZZSkR-G40)

### <a name="dm"></a>  4.2 Dynamic Models
The dynamic models section presents a series of sequence diagrams that depict the interactions between the system components during various operations, emphasizing the behavior and collaboration of objects over time.

#### <a name="rdc"></a> 4.2.1 Retrieving device configuration
![Retrieve Device Config](https://www.plantuml.com/plantuml/png/ZPF1Ri8m44Jl-nK-qWFzW1ugXBbmY1NLN-36Mx04TXexIFtxUZ2DJ1e9hfitipEhU1EJ6T9uFf7fKXY4F3dsYVeyT9thZKz2XI4TFyFehGpdFdZECnE5skxli1rVNCUDyUR00u1TS_TTXt79Hc_bWeqCtdENNF2rePmvSeoPkBhSuhV7d-WwSrB1CkQj8YgLnFeTaSMRE73Azb_kC0u66rRskd9lNs3YBYOnI0ATHRkcQ4gm1HX5ALFhQKDXo7AD53lqVIu3I2BW225lAT1vcGT3uDRJbbXW1AcWoLHV4My_-7lac5RpKBTv7OaqXAr26DNiN__rgE1y-rQLD7_up3xsmSVwJj2GhXey56YKgKx_M82hWeMc_1hFoBA2DkmjNiel)
1. The `Client` sends a `getConfiguration` request to the `NorthboundComponent`. The request includes the `deviceId` of the network device that the configuration is requested for.
2. The `NorthboundComponent` contacts the `DeviceManager` to fetch the device details using the provided `deviceId`.
3. Once the `DeviceManager` provides the device details, the `NorthboundComponent` sends a `getConfiguration` request to the `SouthboundComponent`.
4. The `SouthboundComponent` interacts with the `DeviceConnectionManager` to get a `Session` with the device.
5. After the `DeviceConnectionManager` returns the `Session`, the `SouthboundComponent` uses the `Session` to send a `getConfiguration` request directly to the `PhysicalNode` using the gNMI protocol.
6. The `PhysicalNode` retrieves the configuration details and sends them back to the `Session` as a gNMI response.
7. The `Session` forwards the configuration response to the `SouthboundComponent`.
8. The `SouthboundComponent` forwards the configuration response back to the `NorthboundComponent`.
9. Finally, the `NorthboundComponent` sends the configuration response back to the `Client`.







#### <a name="udc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/png/dPD1RiCW44Ntd89bAjKzG1TL5BpnmiYAJq1XwY0vu1eSgRVlZ4qjaj9Dj-FtFsyWEOQevhXSHwPqz3CNemKNsOH3g-saNEJIp_7oxXTdXBzEtW7ELE3orDnXDToiXbOvDS1CGDtUVU_zKghfJw9G8xnpeAFrBYyK3uqG0X9hIzBlBb_1QZLARu3cdMHiMubNRwJCNtc0YFKVTbXcHISSp7faOvwvpkTFr6blAW856QCqbjGjTWmGDz4zdD6uE4Egsa_S66OWHr8VNS3_fEZc6AL9IYIhT2T5BK5U25RPgIjDPXcrj_OhuimPFXS8yV0ecUQv4sOfsKdIaMtpycknlzQXfyBOAlSJFaEOl0j0WZdNPpkKc3wjiBz04QBxey-4fHz9cKI87S4P-fjy0m00)
This sequence diagram describes the dynamic behavior of the network device connection management system for setting the configuration of a network device:

1. The client initiates the configuration setting operation by sending a `setConfiguration(deviceId, configuration)` request to the `NorthboundComponent`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` by sending a `getDevice(deviceId)` request.
3. The `DeviceManager` returns the device details to the `NorthboundComponent`.
4. The `NorthboundComponent` then sends a `setConfiguration(deviceId, configuration)` request to the `SouthboundComponent`.
5. The `SouthboundComponent` requests the `DeviceConnectionManager` to provide an active session for the device by invoking `getSession(deviceId)`.
6. The `DeviceConnectionManager` provides a `Session` and returns it to the `SouthboundComponent`.
7. The `SouthboundComponent` sends a `sendRequest(setConfigurationRequest)` request to the `Session`.
8. The `Session` sends a `gNMI.setConfiguration(configuration)` request to the `PhysicalNode`.
9. The `PhysicalNode` applies the configuration and sends a `gNMI.Response` back to the `Session`.
10. The `Session` sends the response back to the `SouthboundComponent`.
11. The `SouthboundComponent` sends the response back to the `NorthboundComponent`.
12. The `NorthboundComponent` sends the final response back to the client.




#### <a name="sm"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/png/tPH1QiCm44NtFeN8Ae6u1oYaX2WRBsn2VG95cYG2Mt8bET3RLsEhmafJ5x8efPiGD4___naDtbh7ZElR9cFSQKDe8q6vhFE7aikEAKSgRTpbg7ibg6uxhNoTC4kgNP5WUxXA3YLJx0m6WNsPr6lTtvEfT_IE3DLA0NTIgwaWlL44Qpqng8JuXykxbPmrbHQ0vuSgoyQMIFw2aSapQGV5kZzQRcI77alTWM7uRqt4u5-8DR4Jeb0dlK8FUMKEXjvPuqYyHpORik06V76H4QoZtxxqTcTmOq_BJ-cL9wAKdr96aCnZm97pfm8cM9J5aNbgS4lRcCa-iSJxw8ao69A6b66StyIa603PF2QmuuKaA28JyNh0b7Y5jnwiMtuD7IffG1GOZ0uLPglAukdXTd4720hb8G_LwYHDoroIFP9rx70EhMEJuTbcOEJ2wo4Tzd7k0kuND1Pk5an00x-uO4nnQ9A99tCMVsQfGj07bkhd5l_18YcHRVqFVkG_0000)


This sequence diagram describes the dynamic behavior of the network device subscription management system.

1. The client initiates a subscription management operation by sending a `manageSubscription` request to the `NorthboundComponent`. The request includes the operation to be performed ("create" or "delete"), the `deviceId` of the network device, and `subscriptionInfo`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` using the provided `deviceId`.
3. The `NorthboundComponent` sends a corresponding subscription management request to the `SouthboundComponent`.
   - If the operation is "create":
     - The `NorthboundComponent` asks the `SouthboundComponent` to create a subscription for the device.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to get an active session for the device.
     - The `DeviceConnectionManager` provides an active `Session` to the `SouthboundComponent`.
     - The `SouthboundComponent` uses the `Session` to send a subscription creation request.
     - The `Session` forwards this request directly to the `PhysicalNode` using the gNMI protocol and receives a confirmation in response.
     - The `Session` passes this confirmation back to the `SouthboundComponent`.
     - The `SouthboundComponent` passes the confirmation to the `NorthboundComponent`, which then sends it to the client.
   - If the operation is "delete":
     - The `NorthboundComponent` asks the `SouthboundComponent` to delete the subscription using the `subscriptionId`.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to get an active session for the device.
     - The `DeviceConnectionManager` provides an active `Session` to the `SouthboundComponent`.
     - The `SouthboundComponent` uses the `Session` to send a subscription cancellation request.
     - The `Session` forwards this request directly to the `PhysicalNode` using the gNMI protocol and receives a confirmation in response.
     - The `Session` passes this confirmation back to the `SouthboundComponent`.
     - The `SouthboundComponent` passes the confirmation to the `NorthboundComponent`, which then sends it to the client.




#### <a name="add"></a> 4.2.4 Add/Delete Device
![Add/Delete Device](https://www.plantuml.com/plantuml/png/jPB1JiCm44Jl_efLJo3H7q0ggeWlFQJ_iCHRi72yaRCjlu_N9WZd477fTVncF2FxE0ickOx1u22Se0kUefWfYtxm4qQ1CoVvUEDhT1sF4qVA6ivmVZqrcAMR7wZ7YE-K5B1z6qFonUcpOYN06bElW_sBni4pZCLUcGUUAA5uZa_WYdAA5txKelw6GgK11e5V3Wu7sA5pEmDwgB6spwbP-edycmEm96bLK5Hxs7yOj3iekb-kw3XUV1fBZNpag4KrkuxSOhK7XPbMYnm54bgFgcfRqssksdRSULPqHZ75wwEl7OwQXprcXtxBRm00)

In the Add/Delete Device flow, a client sends a request to manage a device. The operation could be either to add or delete a device. Please note that the creation of physical connections is managed by sessions and isn't shown in this diagram.

1. The client sends a `manageDevice` request to the `NorthboundComponent` specifying the operation ("add" or "delete") and either the `deviceInfo` or `deviceId`.
2. The `NorthboundComponent` requests the `DeviceManager` to either add or delete the device.
3. Depending on the operation:
    - If the operation is "add":
        a. The `NorthboundComponent` sends an `addDevice` request to the `DeviceManager` with the `deviceInfo`.
        b. The `DeviceManager` initiates the creation of a new `NetworkDevice` instance using the provided `deviceInfo`.
        c. The `NetworkDevice` confirms the successful creation to the `DeviceManager`.
    - If the operation is "delete":
        a. The `NorthboundComponent` sends a `deleteDevice` request to the `DeviceManager` with the `deviceId`.
        b. The `DeviceManager` initiates the deletion of the `NetworkDevice` instance associated with the provided `deviceId`.
        c. The `NetworkDevice` confirms the successful deletion to the `DeviceManager`.
4. Finally, the `NorthboundComponent` returns the confirmation to the client.


#### <a name="sc"></a> 4.2.5 Session Creation
![Session Creation](https://www.plantuml.com/plantuml/png/xPF1JiCm38RlUGeVjyDUO5BHe9jWXrKLVO8iCLj4vfGwdU3jYKlAj6f873XogA8wt__xJvskEEekzYUlshGwurfD4PhGn-C-z6JBS6e3OQffXkQXlCBMU7O6oq249hf0EqtwW9sWwt9txOZCYHXSb3OnT50VFzWPxQjWKUfrfPJs4Qe0zydkZD-YbqG_fWBbZbkh043YmE9EMi4I3XWpFhCPcW-K5YyTKJWfo7gHcGxJtYrb1XCfVPPZc677ujHr3DCaaf7iCxxro76M7httZe-NhvIt9kcQBnVngAjaCXN-VB34oxl85Zd1HRCPRT3cv5DPCvxnjxf6eMUSpcZpxZZodu_gzWJ-9q1M5GNKsBbWdO6dq3E-8hQE3b0KwlgE-y2ufK4-NdMUpTKAoSgV_ma0)

This sequence diagram describes the behavior of the session management system when interacting with a network device:

1. In the scenario where no active session exists for a specific `deviceId`, the `SouthboundComponent` sends a `getSession(deviceId)` request to the `DeviceConnectionManager`. Recognizing that there's no active session for the provided `deviceId`, the `DeviceConnectionManager` internally invokes `createSession(deviceId)` to start a new session. Once the session is created, it returns the new session to the `SouthboundComponent`.
2. The `SouthboundComponent` then sends a `sendRequest(establishConnectionRequest)` to the newly created session.
3. The session then sends an `establishConnection(deviceId)` request to the `PhysicalNode`.
4. The `PhysicalNode` acknowledges the connection establishment by returning a `ConnectionEstablished` response back to the Session.
5. The Session forwards this response back to the `SouthboundComponent` to inform it that the connection with the `PhysicalNode` has been successfully established.

In case an active session already exists for a `deviceId`, the flow is slightly different:

1. When the `SouthboundComponent` sends a `getSession(deviceId)` request to the `DeviceConnectionManager`, it returns the existing active session for the `deviceId`.
2. The `SouthboundComponent` then sends a `sendRequest(establishConnectionRequest)` to the existing session.
3. The session sends an `establishConnection(deviceId)` request to the `PhysicalNode` and forwards the `ConnectionEstablished` response back to the `SouthboundComponent`, as in the previous scenario.

Lastly, as a part of periodic housekeeping, the `DeviceConnectionManager` can decide to close inactive sessions by invoking the `closeInactiveSessions()` operation.




