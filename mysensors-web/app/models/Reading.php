<?php

class Reading extends Eloquent {

  protected $hidden = array('sensor_value_id');

  public function sensorValue(){
    return $this->belongsTo("SensorValue");
  }

}

?>
