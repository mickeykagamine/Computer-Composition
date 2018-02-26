`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   09:08:10 12/01/2016
// Design Name:   mips
// Module Name:   G:/ise homework/p4/test.v
// Project Name:  p4
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: mips
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
	reg clk;
	reg reset;

	// Instantiate the Unit Under Test (UUT)
	mips uut (
		.clk(clk), 
		.reset(reset)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
	//	reset = 1;
	//	#5 clk = 1;
		#5 reset = 0;
	//	#5 clk = 0;
	//	#5 clk = 1;

		// Wait 100 ns for global reset to finish
		
        
		// Add stimulus here

	end
	always #10 clk=~clk;
      
endmodule

