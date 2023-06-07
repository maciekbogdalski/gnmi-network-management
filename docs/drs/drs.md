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
![Retrieve Device Config](https://www.plantuml.com/plantuml/png/bP9DKeGm48NtEKMMus8yW0jhQf8D2rYO4qHeCIKac9_n-gPffG935RfDV_twlLUvXAXzJEF0T1kTvs8mO2ERygDfpQHjv8tpyVtL9Ti9DqxEvZdNWJVNQeL9k9aMQcrr3nu1MQ_cogKz6NKLEpB2MGjjDCwMWcAZ225aObPPEuRuvVm7ISrU9MEKZ9-VqJb_uZt4VENDzCbhF7JgPhpg7h0ASzCHa6H8voLPqmw9xi2ObsNgl5mWXdLG8YI7iV_e0GjYYAC9WIvQ3tb0wNTNi2X4pykXG31wAQ15Lys8xLxWCq68fwsld_VI5LDpbH9JD7NrU1Ubh56oHfARNtmZ7oPd0rPLxZ1L93XSK1I6sZzSs2zsSE4VlTwrYOTnHb_js0Dzi4ikFet3Dm00)
1. The `Client` sends a `getConfiguration` request to the `NorthboundComponent`. The request includes the `deviceId` of the network device that the configuration is requested for.
2. The `NorthboundComponent` contacts the `DeviceManager` to fetch the device details using the provided `deviceId`.
3. Once the `DeviceManager` provides the device details, the `NorthboundComponent` sends a `getConfiguration` request to the `SouthboundComponent`.
4. The `SouthboundComponent` interacts with the `DeviceConnectionManager` to create a new session with the device.
5. After the `DeviceConnectionManager` returns the newly created `Session`, the `SouthboundComponent` uses the `Session` to send a `getConfiguration` request. This request uses the gNMI protocol to communicate with the `NetworkDevice`.
6. The `NetworkDevice` receives the gNMI request, retrieves the configuration details, and sends them back to the `Session` as a gNMI response.
7. The `Session` forwards the configuration response to the `SouthboundComponent`.
8. The `SouthboundComponent` then sends a `closeSession` request to the `DeviceConnectionManager` to close the session.
9. After closing the session, the `SouthboundComponent` forwards the configuration response back to the `NorthboundComponent`.
10. Finally, the `NorthboundComponent` sends the configuration response back to the `Client`.





#### <a name="udc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/png/dPD1KWCX44Ntd89bh38Um8ML2cooc2mo9q2cZPG9ZD0JhsytWoc8erQvfL____idiqbe8axdap0MGvJgvC2Z6EdHMJSQZt8V8hu-XyaFAfp7u6akJPBxxQx1D5oSXSvuSuJ8WEwQUH-c9Pb-gnPaLF0UBBhWQq5remWf4P5LsiI07o6-pL8vgnPYlaokdpYvV9G9a5nUt76AXerMGyPtmxsqzViTD-Ck1e4NXU1j4j4TQHm1PvFhSaNJ_OAezTLnPiG03LBqg8V_XU821MzpACMfR0GQb7OMar6HWjbrPPnf2jTIHRZFY1yEy3v1mjLjpl9UnwBTNBRcdlRTxk7RRJ_MhAaLFOVxMZv06eDFt5x3zTKDIqnVJYW_GWFzLk4ffByQlAsAzTcd_D6MhGWI6ogIlxXF)
This sequence diagram describes the dynamic behavior of the network device connection management system for setting the configuration of a network device:

1. The client initiates the configuration setting operation by sending a `setConfiguration(deviceId, configuration)` request to the `NorthboundComponent`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` by sending a `getDevice(deviceId)` request.
3. The `DeviceManager` returns the device details to the `NorthboundComponent`.
4. The `NorthboundComponent` then sends a `sendRequestToDevice(deviceId, setConfigurationRequest)` request to the `SouthboundComponent`.
5. The `SouthboundComponent` requests the `DeviceConnectionManager` to create a new session for the device by invoking `createSession(deviceId)`.
6. The `DeviceConnectionManager` creates a new `Session` and returns it to the `SouthboundComponent`.
7. The `SouthboundComponent` sends a `sendRequest(setConfigurationRequest)` request to the `Session`.
8. The `Session` sends a `performOperation(setConfigurationRequest, gNMI)` request to the `NetworkDevice` using the gNMI protocol.
9. The `NetworkDevice` performs the operation and sends a response back to the `Session`.
10. The `Session` sends the response back to the `SouthboundComponent`.
11. The `SouthboundComponent` sends the response back to the `NorthboundComponent`.
12. The `SouthboundComponent` requests the `DeviceConnectionManager` to close the session by invoking `closeSession(deviceId)`.
13. The `NorthboundComponent` sends the final response back to the client.



#### <a name="sm"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/png/xLN1RjmW5Bpp5IYdZHJt0ogbYe8lFjYAwYyWy995nU02tgf_Nnwuo6otrceFLG-vh5QyOMRUC9AVVE0kB9CcN0JhADCAJ21pF5H2pTm4EbWNZgzsCPBPQROcpYdtT7ZkAbWB9oMWvuQ_WqD0srVpqIwNQCPdTe66MMD016NDbf2TCOBt4P5OQiSGVbZtBLCbh-swtilnfrU2wy7AD7WP2Cahq-OBhaG_qoafZikh5qxDwE5WPt0S_prGcKWx-K3z1j6PDtkF6kh40wHik0wqtAEFZ_HEE8Z3Eq9nZdfj7-NU8MIZXz_Kzn5Hg6AAX2AoAGOpBfvAsC0ABKOQKLcjMcF7-qOIxwCcqg19rXUsDV6_E6Ne0U5DCU7pXSehGZRyOu8P-HM-B-33uK_VwwJsY0JfpLhCSEYxJpSlZFMWIDIiTfWrRyfDF5JM2t8i2-v1HvBJs6IehRycmejHbVVVqKJSMWD4vsBlNi2YWlPmrbG96lvXKxaHeAk7gvvB_aVjp5vlQUVrItuqqaZo57_mi_GB)


This sequence diagram describes the dynamic behavior of the network device subscription management system.

1. The client initiates a subscription management operation by sending a request to the `NorthboundComponent`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` using the `deviceId` provided.
3. Depending on the operation requested by the client, the `NorthboundComponent` interacts with the `SouthboundComponent`.
   - If the operation is "create":
     - The `NorthboundComponent` requests the `SouthboundComponent` to create a subscription for the device.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to create a new session for the device.
     - The `DeviceConnectionManager` creates a new `Session` and returns it to the `SouthboundComponent`.
     - The `SouthboundComponent` sends a subscription creation request to the `Session`.
     - The `Session` requests the `NetworkDevice` to establish a gNMI subscription and returns the confirmation to the `SouthboundComponent`.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to close the session after completion.
     - The `SouthboundComponent` returns the subscription confirmation to the `NorthboundComponent`, which in turn returns it to the client.
   - If the operation is "delete":
     - The `NorthboundComponent` requests the `SouthboundComponent` to cancel a subscription using the `subscriptionId`.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to create a new session for the device.
     - The `DeviceConnectionManager` creates a new `Session` and returns it to the `SouthboundComponent`.
     - The `SouthboundComponent` sends a subscription cancellation request to the `Session`.
     - The `Session` requests the `NetworkDevice` to cancel the gNMI subscription and returns the confirmation to the `SouthboundComponent`.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to close the session after completion.
     - The `SouthboundComponent` returns the subscription cancellation confirmation to the `NorthboundComponent`, which in turn returns it to the client.





#### <a name="dcm"></a> 4.2.4 Device connection management (connect, disconnect)
![Device Connection Management](https://www.plantuml.com/plantuml/png/vLH1Ri8m4Bpx5IjEL2fzG2KgH7BX42gH5vXa2rOJExKT-ltksk0cDAYLUgdK0nRopixCZbTPEI-jxzj6oCeR2rcZK7lHqQMgL2UrXwsn_hWtlQupqtP6KnsaW-rwCuBbU58L5bBB0re6vCMeNff-YgPSPnCqcT4QAw-C7X9cLupe7242ozWn-dTZNoDLy9eB4IU3nHCxXqTecNWWD3STMid_xg4EdPlwZbDH9-anZ2leO8AyeFu3-YWmdq3Jx0HQ9B484JKE4K8s7f8gB9SmgwATcG28MfmFzSVxhu80YP2313wvYwBYBejKESSqtSMP2WZuHL9piOFkQnmZaq0POBhUuLkFpi_f9_UDSiVFGC-biKbc26-GSuJROlD0yxyOspvVSlWf5Rqfq15jNyXsw6Yr70RhOsWv670QLgOaKe2DST_cTpkczFpJUeGH0Xk7LqjGAtTh3wZqvrV1essL9jplLo5H_OTLe0Z5YWx-67y0)

In the Device Connection Management flow, a client sends a request to manage a device's connection. The operation could be either to connect or disconnect.

1. The client sends a `manageConnection` request to the `NorthboundComponent` specifying the operation ("connect" or "disconnect") and the `deviceId`.
2. The `NorthboundComponent` requests the `DeviceManager` to get the device details associated with the `deviceId`.
3. The `DeviceManager` returns the device details to the `NorthboundComponent`.
4. Depending on the operation:
    - If the operation is "connect":
        a. The `NorthboundComponent` requests the `SouthboundComponent` to connect to the device.
        b. The `SouthboundComponent` requests the `DeviceConnectionManager` to create a session associated with the `deviceId`.
        c. The `DeviceConnectionManager` creates a session and returns it to the `SouthboundComponent`.
        d. The `SouthboundComponent` sends a `gNMI.performOperation` request to the `Session` specifying the `establishConnectionRequest`.
        e. The `Session` sends a `gNMI.performOperation` request to the `NetworkDevice` to establish a connection.
        f. The `NetworkDevice` returns a `gNMI.Response` to the `Session`.
        g. The `Session` sends the response back to the `SouthboundComponent`.
        h. The `DeviceConnectionManager` closes the session.
        i. The `SouthboundComponent` returns the response to the `NorthboundComponent`.
    - If the operation is "disconnect":
        a. The `NorthboundComponent` requests the `SouthboundComponent` to disconnect the device.
        b. The `SouthboundComponent` requests the `DeviceConnectionManager` to create a session associated with the `deviceId`.
        c. The `DeviceConnectionManager` creates a session and returns it to the `SouthboundComponent`.
        d. The `SouthboundComponent` sends a `gNMI.performOperation` request to the `Session` specifying the `terminateConnectionRequest`.
        e. The `Session` sends a `gNMI.performOperation` request to the `NetworkDevice` to terminate the connection.
        f. The `NetworkDevice` returns a `gNMI.Response` to the `Session`.
        g. The `Session` sends the response back to the `SouthboundComponent`.
        h. The `DeviceConnectionManager` closes the session.
        i. The `SouthboundComponent` returns the response to the `NorthboundComponent`.
5. Finally, the `NorthboundComponent` returns the response to the client.


