`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:32:50 11/30/2016 
// Design Name: 
// Module Name:    if 
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
module IF(
	input Clk,
	input Branch,
	input Branch2,
	input Zero,
	input Zero2,
	input Reset,
	input [1:0]J,
	input [31:0]structionold,
	input [31:0]C,
	output [31:0]Addr,
	output [31:0]PC4
    );
	 reg [31:0]REG;
	 assign Addr=REG;
	 assign PC4=REG+4;
	 initial begin
		REG<=32'h3000;
	 end
	always@(posedge Clk)begin
		if(Reset)begin
			REG<=32'h3000;
		end
		else REG<=((J==00)?(((Branch2&Zero2)||(Branch&Zero))?(REG+5'b00100+{14'b0,structionold[15:0],2'b00}):(REG+5'b00100))
						:((J==01)?{4'b0,structionold[25:0],2'b0}
						:C));
	end

endmodule
