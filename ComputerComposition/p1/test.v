`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   10:20:54 10/26/2016
// Design Name:   fsm
// Module Name:   G:/ise homework/p1/test.v
// Project Name:  p1
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: fsm
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module test;

	// Inputs
	reg Clk;
	reg Clr;
	reg X;
	// Outputs
	reg Z1;
	reg [1:0]state1;
	
	// Instantiate the Unit Under Test (UUT)
	fsm uut (
		.Clk(Clk), 
		.Clr(Clr), 
		.X(X), 
		.Z2(Z1),
		.state1(state1)
	);

	initial begin
		// Initialize Inputs
		Clk = 0; 
		Clr = 0;
		X = 0;


		// Wait 100 ns for global reset to finish
      #40 X=1;
		#40 X=0;
		#40 X=1;   
		#40 X=0;
		#40 X=1;
		// Add stimulus here

	end
   always #20 Clk=~Clk;   
endmodule

