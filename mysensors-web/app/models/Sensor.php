<?php

class Sensor extends Eloquent {

  protected $hidden = array('id');

  public function node(){
    return $this->belongsTo('Node');
  }

  public function sensorValues(){
    return $this->hasMany('SensorValue');
  }

}

?>
