`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:56:43 12/07/2016 
// Design Name: 
// Module Name:    IFID 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module IFID(
	input Clk,
	input Reset,
	input StallD,
	input [1:0]PCSrcD,
	
	input [31:0]PCPlus4F,
	input [31:0]InstrF,

	output [31:0]InstrD,
	output [31:0]PCPlus4D
    );
	reg [31:0]addr,instruction;
	initial begin
		addr<=32'b0;
		instruction<=32'b0;	
	end
	
   assign PCPlus4D=addr,
			 InstrD=instruction;
	always @(posedge Clk)begin
		if(StallD==0)begin
			if(Reset)begin
				addr<=32'b0;
				instruction<=32'b0;						
			end
			else begin
				addr<=PCPlus4F;
				instruction<=InstrF;
			end
		 end
	end

endmodule
