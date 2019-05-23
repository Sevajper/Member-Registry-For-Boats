# WS2_1dv607

Design and implement a simple member registry with CRUD (Create, Retrieve, Update, Delete) functionality. Implementation (source code + “running” version), class- and interaction-diagrams are to be created and presented. The interaction diagrams should show how a model-view separation is achieved (i.e. start in the ui) and how the different requirements are met. Design and implementation should match. Save diagrams and implementation in your portfolio.

The focus is not to create a usable or fancy user interface but to have a robust and well documented design that can handle change and follows the GRASP. My recommendation is that you base your work on a console application.

OBS: It is not permitted to use any type of framework, however class libraries, api:s etc are permitted. Basically you should design and code your own application.

The following requirements are to be met:

Create a new member with a name, personal number, a unique member id should be created and assigned to the new member.
List all members in two different ways:
“Compact List”; name, member id and number of boats
“Verbose List”; name, personal number, member id and boats with boat information
Delete a member
Change a member’s information
Look at a specific member’s information
Register a new boat for a member the boat should have a type (Sailboat, Motorsailer, kayak/Canoe, Other) and a length.
Deleta a boat
Change a boat’s information
Persistence (the registry should be saved and loaded for example from a text file.)
Strict Model-View separation (The model should not depend on the view or user interface in any way (direct or indirect) the user interface (view) should not implement domain functionality)
Good quality of code (for example naming, standards, duplication)
An object oriented design and implementation. This includes but is not limited to:
Objects are connected using associations and not with keys/ids.
Classes have high cohesion and are not too large or have too much responsibility.
Classes have low coupling and are not too connected to other entities.
Avoid the use of static variables or operations as well as global variables.
Avoid hidden dependencies.
Informations should be encapsulated.
Use a natural design, the domain model should inspire the design.
Simple error handling. The application should not crash but it does not need to be user friendly.
Participate in the peer review process
