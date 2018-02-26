`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:39:11 11/30/2016 
// Design Name: 
// Module Name:    dm 
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
module dm(
    input [9:0] Addr,
	 input [31:0]Data,
    input Clk,
	 input MemWrite,
	 input MemRead,
	 input Reset,
    output [31:0] Out
    );
	integer i;
	reg [31:0] _in[1023:0];
	
	assign Out = _in[Addr];

	always@(posedge Clk)begin
		if(MemWrite)begin
			_in[Addr]<=Data;
			$display("*%h <= %h",{14'b0+Addr*4},Data);
		end
		if(Reset)begin
			for(i=0;i<1024;i=i+1)begin
				_in[i]<=31'b0;
			end
		end
	end
	
	initial begin
		for(i=0;i<1024;i=i+1)begin
			_in[i]<=31'b0;
		end
	end

endmodule
