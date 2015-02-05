<?php

class Node extends Eloquent {

  public function sensors(){
    return $this->hasMany("Sensor");
  }

}

?>
