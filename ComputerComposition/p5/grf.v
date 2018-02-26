`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    11:10:03 11/25/2016 
// Design Name: 
// Module Name:    grf 
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
module grf(
    input Clk,
    input Reset,
    input [4:0] RS1,
    input [4:0] RS2,
    input [4:0] RD,
    input RegWrite,
    input [31:0] WData,
	 input [1:0]J,
	 
    output [31:0] RData1,
    output [31:0] RData2
    );
	reg [31:0] _reg[31:0];
	integer i;
	
	assign RData1=(RegWrite==1&&RS1==RD&&RS1!=0)?WData:_reg[RS1];
	assign RData2=(RegWrite==1&&RS2==RD&&RS2!=0)?WData:_reg[RS2];
	
	initial begin
		for(i=0;i<32;i=i+1)begin
			_reg[i] = 0;
		end
	end
	
	always @ (posedge Clk )begin
		if(Reset)begin
			for(i=0;i<32;i=i+1)begin
				_reg[i] <= 0;
			end
		end
		else begin
			if(RegWrite&&RD!=0)begin
				_reg[RD]<= WData;
				$display("$%d <= %h",RD,WData);
			end
		end
	end

endmodule
