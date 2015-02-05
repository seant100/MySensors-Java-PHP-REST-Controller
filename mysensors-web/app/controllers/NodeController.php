<?php

use Illuminate\Database\Eloquent\ModelNotFoundException;

App::error(function(ModelNotFoundException $e)
{
	return Response::json(array("Not Found"), 404);
});

class NodeController extends \BaseController {

	public function index()
	{
		return Node::with('sensors', 'sensors.sensorValues')->orderBy('id')->get();
	}

	public function store()
	{
		$node = new Node;

		if(null !== Request::get('name')){
			$node->name = Request::get('name');
		}

		if(null !== Request::get('sketchName')){
			$node->sketch_name = Request::get('sketchName');
		}

		if(null !== Request::get('sketchVersion')){
			$node->sketch_version = Request::get('sketchVersion');
		}

		if(null !== Request::get('batteryPercent')){
			$node->battery_percent = Request::get('batteryPercent');
		}

		$node->save();
		return Node::with('sensors', 'sensors.sensorValues')->findOrFail($node->id);
	}

	public function show($id)
	{
		return Node::with('sensors', 'sensors.sensorValues')->findOrFail($id);
	}

	public function update($id)
	{
		$node = Node::findOrFail($id);

		if(null !== Request::get('name')){
			$node->name = Request::get('name');
		}

		if(null !== Request::get('sketchName')){
			$node->sketch_name = Request::get('sketchName');
		}

		if(null !== Request::get('sketchVersion')){
			$node->sketch_version = Request::get('sketchVersion');
		}

		if(null !== Request::get('batteryPercent')){
			$node->battery_percent = Request::get('batteryPercent');
		}

		$node->save();

		return Node::with('sensors', 'sensors.sensorValues')->findOrFail($id);
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		return "Destroy ".$id;
	}


}
