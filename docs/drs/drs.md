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
![Retrieve Device Config](https://www.plantuml.com/plantuml/png/ZPD1RiCW44NtSmgMwS8zG1TL5DXaOHIL4r0pJL0JS06dwktBcCZ2hgLw2-__-VEnzp7fa8RR5NINV63yQj4bwFEXxMolNMBIXtHvzuCptDzwx_8zqv79mt662RpR3bljz1a30QATtIi_hDce0r-nuTuvx9BrhZRa2qUCCHEZopmnfcyVFelLc5KyrZjTVgBjz5LwCrwS941PcJMlj19xOMTCUVo7FGz1KuAT6NsEvebCx5qd91A8pYBH5asPjW9J4P2fPffG631O8yME-jYMWKW0mYa4VwIWJjPvOe2uPfeI2rm58MWoLIFYp1j-3HZJRXdgSLv70aL2AIYCR8_FV_QeEnGW1U4dkG7F56ImcTaluiJUkqYzrY8foiPRDPGBLDNIlm9L2YlFkqb0BqSiQShdjqMM5R17P_BV-Wi0)
1. The `Client` sends a `getConfiguration` request to the `NorthboundComponent`. The request includes the `deviceId` of the network device that the configuration is requested for.
2. The `NorthboundComponent` contacts the `DeviceManager` to fetch the device details using the provided `deviceId`.
3. Once the `DeviceManager` provides the device details, the `NorthboundComponent` sends a `getConfiguration` request to the `SouthboundComponent`.
4. The `SouthboundComponent` interacts with the `DeviceConnectionManager` to get a `Session` with the device.
5. After the `DeviceConnectionManager` returns the `Session`, the `SouthboundComponent` uses the `Session` to send a `getConfiguration` request. This request uses the gNMI protocol to communicate with the `NetworkDevice`.
6. The `NetworkDevice` receives the gNMI request and sends the same request to the `PhysicalNode` using the gNMI protocol.
7. The `PhysicalNode` retrieves the configuration details and sends them back to the `NetworkDevice` as a gNMI response.
8. The `NetworkDevice` then forwards the gNMI response to the `Session`.
9. The `Session` forwards the configuration response to the `SouthboundComponent`.
10. The `SouthboundComponent` forwards the configuration response back to the `NorthboundComponent`.
11. Finally, the `NorthboundComponent` sends the configuration response back to the `Client`.






#### <a name="udc"></a> 4.2.2 Updating device configuration
![Updating device configuration](https://www.plantuml.com/plantuml/png/dPD1JiCm44NtFeMNHI8SW0Mg6c-wI5Gr9p39q5gaTeYT8cxFZ3Egx68Mi9sy_-VFTxlrGSzXkOn2zy7DiXuDs20c79hUJDe6sRevd5_TOeVQNIPd0MVQotQtpp05LzD3ewq-mKo0QhBldLjADjskBjZKpbheWt4sDQpl7C5x9A9BdXZ2fvlVLwkOLV5vX_ENDxqUMpV43uTMYFLaMRtGIV9PUWYu_isSbbbJWiqGVVR3e-pJ-GDvcgiE4BiGf4OJrQ372SAw_IPEQ2n685NTDgwC6213s0yB-byeQbQGcaBLd8exAyR2UWM1LR8rqfWifzYrYuWTZl2nW0-R-v0yJpEXDZQjA4xRx9z-7FPhnmehKIG-j7yLeqIWgies7y5FplgiT-JY4Zwkp76OBYcgn7JS4pzp6KA2Nnen_YMMIOJ45ikc_-et)
This sequence diagram describes the dynamic behavior of the network device connection management system for setting the configuration of a network device:

1. The client initiates the configuration setting operation by sending a `setConfiguration(deviceId, configuration)` request to the `NorthboundComponent`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` by sending a `getDevice(deviceId)` request.
3. The `DeviceManager` returns the device details to the `NorthboundComponent`.
4. The `NorthboundComponent` then sends a `setConfiguration(deviceId, configuration)` request to the `SouthboundComponent`.
5. The `SouthboundComponent` requests the `DeviceConnectionManager` to provide an active session for the device by invoking `getSession(deviceId)`.
6. The `DeviceConnectionManager` provides a `Session` and returns it to the `SouthboundComponent`.
7. The `SouthboundComponent` sends a `sendRequest(setConfigurationRequest)` request to the `Session`.
8. The `Session` sends a `gNMI.setConfiguration(configuration)` request to the `NetworkDevice` using the gNMI protocol.
9. The `NetworkDevice` sends a `gNMI.setConfiguration(configuration)` request to the `PhysicalNode`.
10. The `PhysicalNode` applies the configuration and sends a `gNMI.Response` back to the `NetworkDevice`.
11. The `NetworkDevice` forwards the response back to the `Session`.
12. The `Session` sends the response back to the `SouthboundComponent`.
13. The `SouthboundComponent` sends the response back to the `NorthboundComponent`.
14. The `NorthboundComponent` sends the final response back to the client.



#### <a name="sm"></a> 4.2.3 Subscription management (create, update, delete)
![Subscription management](https://www.plantuml.com/plantuml/png/vPN1Qjmm48RlUeeXfmt4VO12IeXzyS4cr4-WI9D4r9PSIKxfsrSZUOMrNIzi2wMGYp6PV_xvPlo7VN2UMxzC8-F26mlrg51xDeS_Ag5chZtqnlgtPxDeMPjfDZhKWJle7zj2rk2x4jXnpL_Hag3fYlfWbdCsmsDzngOsMgFmokYjONtYYCu5HNGfYT7_CFPRiegipRhUqzjFfmGVUoDZuQbdBAqCrHTQ2Jx352SEoxCJLit4S30pMavlTo2ZQIllm6qKhNunjpH3lNEFyJPyz93xuFuUReJ5KBnXG7MQrtHXt2lw17euMjy6HROALsH0oYe39Xs3yBAHPLiwQL2bQSKQ--pRaTHFCyc68EkLSZtt3cPD0AIjCe5B3Haq2xCv356cvLVylg3pXz-Xrqe9I0RKtZU4rdVjfp_Ubh81uK7S8Gv_xKUH0ZAgZdmXoo_AJjpdMsoaUVImgyoGGxxj9UcGpvi3ioicNSfDLAV_X8iDb6mS7Pv4M-A8_p3QN0iSYuzJV1BvtyGvWruPv-isB2DylSS7YQsMx24ywCVk5m00)


This sequence diagram describes the dynamic behavior of the network device subscription management system.

1. The client initiates a subscription management operation by sending a `manageSubscription` request to the `NorthboundComponent`. The request includes the operation to be performed ("create" or "delete"), the `deviceId` of the network device, and `subscriptionInfo`.
2. The `NorthboundComponent` retrieves the device details from the `DeviceManager` using the provided `deviceId`.
3. The `NorthboundComponent` sends a corresponding subscription management request to the `SouthboundComponent`.
   - If the operation is "create":
     - The `NorthboundComponent` asks the `SouthboundComponent` to create a subscription for the device.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to get an active session for the device.
     - The `DeviceConnectionManager` provides an active `Session` to the `SouthboundComponent`.
     - The `SouthboundComponent` uses the `Session` to send a subscription creation request.
     - The `Session` forwards this request to the `NetworkDevice` using the gNMI protocol.
     - The `NetworkDevice` passes the gNMI request to the `PhysicalNode` and receives a confirmation in response.
     - The `Session` passes this confirmation back to the `SouthboundComponent`.
     - The `SouthboundComponent` passes the confirmation to the `NorthboundComponent`, which then sends it to the client.
   - If the operation is "delete":
     - The `NorthboundComponent` asks the `SouthboundComponent` to delete the subscription using the `subscriptionId`.
     - The `SouthboundComponent` requests the `DeviceConnectionManager` to get an active session for the device.
     - The `DeviceConnectionManager` provides an active `Session` to the `SouthboundComponent`.
     - The `SouthboundComponent` uses the `Session` to send a subscription cancellation request.
     - The `Session` forwards this request to the `NetworkDevice` using the gNMI protocol.
     - The `NetworkDevice` passes the gNMI request to the `PhysicalNode` and receives a confirmation in response.
     - The `Session` passes this confirmation back to the `SouthboundComponent`.
     - The `SouthboundComponent` passes the confirmation to the `NorthboundComponent`, which then sends it to the client.



#### <a name="add"></a> 4.2.4 Add/Delete Device
![Add/Delete Device](https://www.plantuml.com/plantuml/png/rPD1Ri9034NtSmgBLIkL2rIYGYKR5aHSmSsOCkh4ZYQ6ghUlPn8GYKJ5jbld_M__hycwLqnwQgF3HYN19WPYTPqDGnCwP8LQaXu_vCH-8sqdJ3R37khtxGIhw1mQsY7Z9wKCLBkf3UctfAy1AmRLk7D__Eb3Wx4MNpxiQ-U6Es3vblV0AxJ5Tn0_IKS9DGY_W2-JBH_aERk5CogLqp0gN3bOhM21tYyS53-phNRcQgFHyjO7uEfa6I2Z-Oxg4O65WeoQO5-Re4bauql2ZJ2JeSAt6iiBcLvUjXXs2Ad54VGqHRDzIV0NQjHu-hN3-wp5fDZJh31FaPJcdGtJQGl-VcaFAUQjAQKsy3yfZhqphE33gvqhF4rUvjeK-OVx1G00)

In the Add/Delete Device flow, a client sends a request to manage a device. The operation could be either to add or delete a device.

1. The client sends a `manageDevice` request to the `NorthboundComponent` specifying the operation ("add" or "delete") and either the `deviceInfo` or `deviceId`.
2. The `NorthboundComponent` requests the `DeviceManager` to either add or delete the device.
3. Depending on the operation:
    - If the operation is "add":
        a. The `NorthboundComponent` sends an `addDevice` request to the `DeviceManager` with the `deviceInfo`.
        b. The `DeviceManager` adds the device to its registry.
        c. The `NetworkDevice` communicates with the `PhysicalNode` to create a physical connection.
        d. After receiving the confirmation from the `PhysicalNode`, the `NetworkDevice` returns the confirmation to the `DeviceManager`.
        e. The `DeviceManager` then sends a confirmation back to the `NorthboundComponent`.
    - If the operation is "delete":
        a. The `NorthboundComponent` sends a `deleteDevice` request to the `DeviceManager` with the `deviceId`.
        b. The `DeviceManager` prepares to remove the device from its registry.
        c. The `NetworkDevice` communicates with the `PhysicalNode` to terminate the physical connection.
        d. After receiving the confirmation from the `PhysicalNode`, the `NetworkDevice` returns the confirmation to the `DeviceManager`.
        e. The `DeviceManager` removes the device from its registry and sends a confirmation back to the `NorthboundComponent`.
4. Finally, the `NorthboundComponent` returns the confirmation to the client.


#### <a name="sc"></a> 4.2.5 Session Creation
![Session Creation](https://www.plantuml.com/plantuml/png/VP4nRiGW44LxJa6vAVO2AQ9LS8eKkmqdO64KezY31TY-VZx43g99PSdpvltvufQoYNcT9x7WwwrV36Ufmvh7PrZPgJ0lWGd_J9BwNNNOG9ktf08psUm3tmsRBue57TIzDr9A8Agb6pmexo5-_whA-348WI1vVIiI-Ifj99Ff45nS1J_TYp38t32fMQAmMD5OODhbFxY67oY2m1BwFoUVnjRYqjXw3BZhOMwG_3WNoGb7tGBiv_5_lLKfqQFwE1ei-ZOMJ5VjHkpA0_q0)

In the Session Creation flow, a session is established with a network device for further interaction.

1. The process begins with the `SouthboundComponent` sending a `createSession` request to the `DeviceConnectionManager`, specifying the `deviceId`.
2. The `DeviceConnectionManager` initiates the process of creating a new `Session` by passing the `deviceId` to the `Session`.
3. The `Session` sends an `establishConnection` request to the `NetworkDevice`.
4. Upon successful connection, the `NetworkDevice` returns a `Connection Established` confirmation to the `Session`.
5. The `Session` sends a `Session Created` confirmation to the `DeviceConnectionManager`.
6. Finally, the `DeviceConnectionManager` returns the newly created session to the `SouthboundComponent`.



