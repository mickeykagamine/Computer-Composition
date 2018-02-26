`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   10:18:02 10/26/2016
// Design Name:   fsm
// Module Name:   G:/ise homework/p1/fsmtestbag.v
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

module fsmtestbag;

	// Inputs
	reg Clk;
	reg Clr;
	reg X;

	// Outputs
	wire Z;

	// Instantiate the Unit Under Test (UUT)
	fsm uut (
		.Clk(Clk), 
		.Clr(Clr), 
		.X(X), 
		.Z(Z)
	);

	initial begin
		// Initialize Inputs
		Clk = 0;
		Clr = 0;
		X = 0;
		state=2'b00;
		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

	end
      
endmodule

