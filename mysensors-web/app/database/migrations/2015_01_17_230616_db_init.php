<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class DbInit extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('nodes', function($table)
		{
			$table->increments('id');
			$table->string('name');
			$table->string('sketch_name');
			$table->string('sketch_version');
			$table->integer('battery_percent')->default(-1);
			$table->timestamps();
		});

		Schema::create('sensors', function($table)
		{
			$table->integer('node_id');
			$table->integer('sensor_id');
			$table->timestamps();
			$table->primary(array('node_id', 'sensor_id'));
		});

		Schema::create('sensor_values', function($table)
		{
			$table->increments('id');
			$table->integer('sensor_id');
			$table->integer('value_type');
			$table->timestamps();
		});

		Schema::create('readings', function($table)
		{
			$table->increments('id');
			$table->integer('sensor_value_id');
			$table->string('value');
			$table->timestamp('created_at')->default(DB::raw('CURRENT_TIMESTAMP'));
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('nodes');
		Schema::drop('sensors');
		Schema::drop('sensor_values');
		Schema::drop('readings');
	}

}
