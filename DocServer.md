# Java TCP server functions Documentation

## Functions Format

UserID FunctionName Param1 Param2 ...

## Function errors 

### `Err_id`

The requested ID doesn't exist in the table

### `Err_wrong_id`
The id of the command doesn't correspond to the client's

### `Err_req_value_format`

The parameter format is wrong

### `Err_req_format`

All other errors

## Available Functions

### `addHeartRate`

params : Double heartRate

adds the heart value to the dictionary of values of the user

example for user 1 :
- input : `1 addHeartRate 120`
- output : `OK`

### `getUserHeartRates`

returns the dictionary of heart rate and timestamps of the user

example for user 1 :
- input : `1 getUserHeartRates`
- output : `{120.0=30/10/2025 07:03:50}`

### `addReactionTime`

params : Double reactionTime

adds the reaction time to the list of values of the user

example for user 1 :
- input : `1 addReactionTime 1.2`
- output : `OK`

### `getUserReactionTimes`

returns the list of reaction times of the user

example for user 1 : 
- input : `1 getUserReactionTimes`
- output : `[1.2]`

### `getAll`

displays on the server side all the values of all the users

example :
- input : `1 getAll`
- output (Server side) :  
` ServerData{data={1=Reaction Time: [1.2],
Heart Rate Data: {120.0=30/10/2025 07:05:32},
Right Accelerometre: {},
Left Accelerometre: {},
Body Temperature: {}
}}`