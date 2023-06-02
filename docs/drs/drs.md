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
![System Architecture](https://www.plantuml.com/plantuml/png/XS-nJWCn30RWtKzXTRy-G0TK2POET45CLGVVnS2f5_kKE5G8yUv4N0wGei0Q_v__U1-DWYK_0SmqNEYPSNFKO2-z9d5ugtvMOR4DlWFYwMVqFJd3no_xeAbbowYoZenwYXdVyUiuCDwJv5sXiEMnl7s1qv7jgk62ooYUiNDwbUwcLR_3nyWHglrTcZDgtBihEOVzMqtNnlelgui0enY79yhkK69BAhVu9ED0Dgf0exyq_4NNmnfdRxV_wOEV9_Ov707sB2xvwHC0)


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
![Class Diagram](https://www.plantuml.com/plantuml/png/bLN1Rjim3BtxAuYSqcqsjgjH51tQIu2jXsNx04LYNM6sb8boUYZsxvDCrR0yDKKkaI0-FZu-KyejUU7yEFILx0KHF5ZddmvsDEhE3aThq7XugG0-7fsLIFG3_un8VkjuRI4TNCKTXGJ2Y9OEXSTxF6c9MpKjE_DeMxYVzo7ZPBMAQ8Kzhj6gXRrtsdGBN8UUGTzqa102SRtcevlgRvLwsDlnR0-Sy3tiH8VqNy5FC72eXOHPI20qAhNyqp9HWM3jJYA84iM1l1FI0sjDMeG9PHoh54fbn2qye7-sxZUVBiGu7Epf4jzAe8myGzDiyHB3JclZqJqAYR3BWi7nOJHQ2g-jWPV4Lx182I_2E8JVrUZGPN2UjFru8EdqCLOjPDCYp7EuJ5XDenH6OfynPlbhX-V1owsScg_NMZA3Toewd08powkHng2C1RFXIEqYKQdJoCQX2TjRcg74wndr2VccxZgZM3IJ4bCtvzlCxb3X7SccMw8-lTxJI96sKN9-RwwRflXojV2Ba5RWpPSDsDhfxidNiFdSD1ysgo_NmjUkSzZn46yvaQwgqiCIsUewBcz-k417PAMUwAerAfUbxBC9R45ISZLaJyL8ivYxE01HJ8el1UHTv6LBzZJDpTdho_OLssR_tYVO3SSUXv11rIqQ5Vze_W40)

##### <a name="cd-description"></a>  4.1.1.1 Class Description
| Class | Description |
|-------|-------------|
| NorthboundComponent | Handles the requests coming from the Northbound interface. It processes the requests and sends them to the appropriate classes for further processing. |
| SouthboundComponent | Responsible for handling and processing device messages. It communicates with the devices using the `Session` instances created by the `DeviceConnectionManager`. It implements the `INetworkDeviceCommunication` interface, defining the behavior for communicating with network devices. |
| INetworkDeviceCommunication | This is an interface that defines the methods for communication with the network devices. It is implemented by the `SouthboundComponent` class. |
| DeviceManager | Handles the addition, removal, and retrieval of `NetworkDevice` instances. It maintains a map of device identifiers to their respective `NetworkDevice` instances. |
| NetworkDevice | Represents a network device in the system. Each device has a unique identifier. |
| DeviceConnectionManager | Manages the creation and retrieval of `Session` instances for each network device. |
| Session | Represents a connection to a network device. Each session allows sending requests to a device and receiving messages from it. |



#### <a name="od"></a>  4.1.2 Object Diagram
![Object diagram](https://www.plantuml.com/plantuml/png/VP8nRuGm38Nt_0hhzXkuyNH29y7IeLFNBY6uaAgngyHf_tv3MYH2mstulSyFsyezYFOItOVYuHsD0B6NUU18OyVkamb9LYjmFB96_B86dpNf2NqfTaoKAikqimE6aDIcn7VbHQdAH70-j-K_uGgtMqoHRQiAOuCs6NAB_f2xbDpX09LxRCT8ThMjVfPoWpgIszUpPTvur99RLldVleQTYsJDmi6tbJd1YbWGl9rc0NxRHhxyVi9JVuK6NgdFIRay_QMUbBeZZSkR-G40)

### <a name="dm"></a>  4.2 Dynamic Models
The dynamic models section presents a series of sequence diagrams that depict the interactions between the system components during various operations, emphasizing the behavior and collaboration of objects over time.

#### <a name="rdc"></a> 4.2.1 Retrieving device configuration
![Retrieve Device Config](https://www.plantuml.com/plantuml/png/lPInJiGm38PtFqN6kw4lm81EAiiDru5xWj2O4j6p8KdBwzEII139LYa3Koiht-S_YTjJJ3QawJQ2xHC7quuUAG7afsdkJSSXlJpnHAxbsnlJMhup0wQMwTaFKx39CnqSphx7ipkk8Z_RX1898EcMBWzV-CMI7J1aVov9ucenGCqsQkPi0eUoICrsvKcmzG4NnHLZbBSlOG2bA6-_PI9SSAOIwJTj51H0SpRgbcBk0WwtxO91EdhTB0Xsta14Se_uFc5CX_xdC9RY7--YdFNE1Xa2A2__dhh3zC7XDT-Nz85-U-kr03MgZqULrXncRytGNSv7SAYevR3L6LVIpVaed1vIznLqr-JkphSuH7Sx-WsTGC89oQs_vay0)
1. The `Client` sends a `getConfiguration(deviceId)` request to the `NorthboundComponent`.
2. The `NorthboundComponent` calls `getDevice(deviceId)` method of the `DeviceManager` to retrieve the `NetworkDevice` instance corresponding to the provided `deviceId`.
3. The `NorthboundComponent` then requests `DeviceConnectionManager` to create a `Session` for communication with the device by calling `createSession(deviceId)`.
4. Once the `DeviceConnectionManager` returns the `Session`, the `NorthboundComponent` uses the session to send a `getConfiguration()` request. This request is routed through the `SouthboundComponent` by calling `sendRequestToDevice(deviceId, request)`.
5. The `SouthboundComponent` communicates with the `NetworkDevice` to fetch the configuration by calling `get(deviceId, data)`.
6. After the `NetworkDevice` returns the configuration, the `SouthboundComponent` sends the configuration back to the `Session`, which then returns it to the `NorthboundComponent`.
7. Finally, the `NorthboundComponent` returns the configuration to the `Client`.

This process enables the system to retrieve device configurations dynamically based on the `Client`'s request, ensuring that the `Client` always has access to up-to-date device information.



#### <a name="udc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/png/hPF1ReCm38RlVeeSEwcym0vJLRZq09FANY03bqLh4vQOxlLd5uHmnwGUTk0IV_xz-q_O9RAHXiiPR4CXclpiq1FqVEWQrrjFfWgHFjx3uDiyNFhWkMviCjN-eB02hwx1qdhROHIWA5Mz3iEQJBtFLsJou3qsv89V2kPtYfWI4pSLxHZfEyJFKKhALG4mRcQo5t5kdar2uYadrmtHog1DUyCFxTOqo_CdISPTBQ4q0d-YKPGiqI6DC-RU1Sph0rFPF71aeCKbCibn2amq4VbuMcjDap40ORc5qtYaXJ4GD9kdf15MHXZYucnMGMguyflGjqVy6Z3HMxXBOlihu8dKw-XjED1_M6MEgDOUDmyuWbeQgu8xUuod42-lFOxqN-rRqrNbOVbC2c21R39mnDG7dzJ5Cm7rlCSwKwiRKOmAKAf2JUzx7MG8OCTHo9_-0m00)
1. The Client sends a `setConfiguration()` request to the `NorthboundComponent`, which includes the new configuration.
2. The `NorthboundComponent` retrieves the device (identified by the provided `deviceId`) from the `DeviceManager`.
3. The `NorthboundComponent` then asks the `SouthboundComponent` to create a new `Session` for the device.
4. The `SouthboundComponent` communicates with `DeviceConnectionManager` to create a `Session`.
5. The `DeviceConnectionManager` returns the newly created `Session` to the `SouthboundComponent`, which in turn passes it back to the `NorthboundComponent`.
6. The `NorthboundComponent` sends a request to update the device configuration to the `SouthboundComponent`.
7. The `SouthboundComponent` forwards the request to the `Session` via the `DeviceConnectionManager`.
8. The `Session` communicates with the `NetworkDevice` to apply the new configuration.
9. Once the `Session` confirms that the configuration update is complete, it sends a confirmation back to the `SouthboundComponent`.
10. The `SouthboundComponent` then sends a confirmation back to the `NorthboundComponent`.
11. The `NorthboundComponent` then sends a confirmation back to the Client.

This process allows the system to dynamically update device configurations based on the Client's request. This ensures that device configurations can be updated as needed without manual intervention.



#### <a name="sm"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/png/vPNFQjmm4CRlUeeX9mVYFa0X9SI--62pr4-WI9D4r9PSIKxfsrSZRSKgwvgQGdle9GJFDx_vD7_O1-UvzUiyCIwyiT1C2hLdI_YeX5guzZ0Owr-VpAfbO-R5w107xc1ux0fPYszAOCyrVq5BWhOlugDPjp3ZOxE1QOpMABmo-XBOl2EYSq4HAQLZzD-C_P9GqMjxVjxfzRjJWa-3aJ5m6XXBJuRw4pq9Fi8SAuxhanDMBUIXCWjQJl_TWGwPdRm3Tn7lzBEvfGhgZNkCdU6JXvm5z_Tm8oo6u0q3YbErjW_5Nj0dcrK2tuPu1eKECY1TdSt9A0vV9Lx8CfJQ6LIfrha_LSefsIGaEEd9IzF_NXhD0AAypjLSIYXDHI49ilbj8KasxPSJ-5NRTzuqnefQViQlApfVNLFFaP91WBWCBOrdwBiFrucvD_k6QE-0G05t6luSHziAHAf_-ckCVbPsvhviQzB6qd5CkyfeSippt9EEh9pflZWFbjJfodOJw6vmSlZkS2HE-9SEXwY7huPo_lt9S2rmAfQgM2Lvv50Iw_YX73DH7iTnn7zo45goX_27Vg9_0000)


1. The `Client` sends a subscription management request to the `NorthboundComponent`. The request includes the operation to be performed (create or delete), the device ID, and, in the case of creation, the subscription information.

2. If the operation is "create":

   - The `NorthboundComponent` calls the `getDevice()` method of the `DeviceManager` to fetch the device details using the provided device ID.
   - The `DeviceManager` returns the `NetworkDevice` object corresponding to the device ID back to the `NorthboundComponent`.
   - The `NorthboundComponent` then requests the `SouthboundComponent` to create a new session with the device via the `createSession()` method.
   - Once the `SouthboundComponent` returns the newly created `Session`, the `SouthboundComponent` uses the session to send a create subscription request.
   - The `SouthboundComponent` then calls the `createSubscription()` method of the `INetworkDeviceCommunication` interface to create the subscription with the provided details.
   - Once the subscription is successfully created, a response is sent back up the chain to the `NorthboundComponent`, which is finally sent back to the `Client`.

3. If the operation is "delete":

   - The `NorthboundComponent` requests the `SouthboundComponent` to retrieve the existing session with the device via the `getSession()` method.
   - Once the `SouthboundComponent` returns the existing `Session`, the `SouthboundComponent` uses the session to send a cancel subscription request.
   - The `SouthboundComponent` calls the `cancelSubscription()` method of the `INetworkDeviceCommunication` interface to cancel the subscription with the provided subscription ID.
   - A response confirming the cancellation of the subscription is sent back up to the `NorthboundComponent` and is finally sent back to the `Client`.



#### <a name="dcm"></a> 4.2.4 Device connection management (connect, disconnect)
![Device Connection Management](https://www.plantuml.com/plantuml/png/lPD1QlCm48NtEiMGLNxuqmCKKaBiJHRseZc18Ws9g2qPQPnUlpC8XDmwjDrqCoJIzzxJFF0XaOuqZuFIXaA4Pd3eIKryw8oRj2VeGwJR9SpUDc6SWkTxq0dwusc1jNXt1ZljzHMZ06stk3-7UStcV6nMR9hWFHfomTU6pTAnHteFyIqh1EXRfV82i7kH1y8pZAAl_BPXmgZbrt-meiIJ_IVBkxic595IFCIWxLX_HSe1snMQLsHeLy8oeYpMXD83GKc5_Hus9Zzdem1objJ0UXEHHMTCYSag4A0OIbyWarNIoCzKKXt_5tvNqb9MyBd51Pdzore5BBbzAytqtUa94-dBuDAjQhXEPbG1Zohs5TCKVC9bBdFBs7Km5w1mIFYfK-lImrg7a7xTwlSYvhvsEXl3ceUTrjqHnj5v1lwqE_HM7NZ8D_y1)

This sequence diagram describes the dynamic behavior of the network device connection management system.

1. The client initiates a connection management operation by sending a request to the `NorthboundComponent`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager`.
3. Depending on the operation requested by the client, the `NorthboundComponent` then interacts with the `SouthboundComponent`.
    - If the operation is "connect":
        - The `NorthboundComponent` requests the `SouthboundComponent` to create a new session for the device.
        - The `SouthboundComponent` creates a new `Session` and returns it to the `NorthboundComponent`.
        - The `NorthboundComponent` sends a connection request to the `Session`.
        - The `Session` requests the `SouthboundComponent` to establish a gNMI connection with the `NetworkDevice`.
        - The `SouthboundComponent` establishes the connection and returns the response to the `Session`, which then returns it to the `NorthboundComponent`.
    - If the operation is "disconnect":
        - The `NorthboundComponent` requests the `SouthboundComponent` to retrieve the session associated with the device.
        - The `SouthboundComponent` retrieves the `Session` and returns it to the `NorthboundComponent`.
        - The `NorthboundComponent` sends a disconnection request to the `Session`.
        - The `Session` requests the `SouthboundComponent` to terminate the gNMI connection with the `NetworkDevice`.
        - The `SouthboundComponent` terminates the connection and returns the response to the `Session`, which then returns it to the `NorthboundComponent`.


