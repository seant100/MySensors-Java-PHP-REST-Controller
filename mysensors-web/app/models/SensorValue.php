<?php

class SensorValue extends Eloquent {

  protected $hidden = array('sensor_id');

  public function sensor(){
    return $this->belongsTo('Sensor');
  }

  public function lastReading(){
    return Reading::where('sensor_value_id', '=', $this->id)->orderBy('created_at', 'desc')->get()->first();
  }

}

?>
