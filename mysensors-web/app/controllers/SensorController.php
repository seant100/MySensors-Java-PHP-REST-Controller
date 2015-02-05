<?php

use Illuminate\Database\Eloquent\ModelNotFoundException;

App::error(function(ModelNotFoundException $e)
{
	return Response::json(array("Not Found"), 404);
});

class SensorController extends \BaseController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index($nodeId)
	{
		return Node::with('sensors', 'sensors.sensorValues')->findOrFail($nodeId)->sensors;
	}

	public function store($nodeId)
	{
		//find the parent node in the db (we are creating a new sensor for the node)
		$node = Node::findOrFail($nodeId);
		$sensor_id = Request::get('sensor_id');

		$existsCheck = Sensor::where('node_id', '=', $nodeId)->where('sensor_id', '=', $sensor_id)->get();

		//return $existsCheck;
		//return $sensor_id;

		if(count($existsCheck) > 0){
			return Response::json(array("Sensor ID already exists for Node"), 500);
		}

		if((null !== $sensor_id) && ($sensor_id < 255) && ($sensor_id >= 0)){
			$sensor = new Sensor();
			$sensor->sensor_id = $sensor_id;
			$sensor->node_id = $nodeId;
			$sensor->save();
		} else {
			return Response::json(array("Sensor ID must be provided, and be between 0 and 254"), 500);
		}

		return Sensor::with('sensorValues')->where('node_id', '=', $nodeId)->where('sensor_id', '=', $sensor->sensor_id)->first();
	}


	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($nodeId, $id)
	{
		$sensor = Sensor::with('sensorValues')->where('sensor_id', '=', $id)->where('node_id', '=', $nodeId)->get();

		if(count($sensor) != 1){
			throw new ModelNotFoundException('Not found');
		}

		return $sensor[0];
	}

	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($nodeId, $id)
	{
		//
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($nodeId, $id)
	{
		//
	}


}
