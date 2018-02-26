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
	 input [31:0]PC4,
    input Clk,
    input Reset,
    input [4:0] RS1,
    input [4:0] RS2,
    input [4:0] RD,
    input RegWrite,
    input [31:0] WData,
	 input [1:0]J,
	 input bgezal,
	 input [31:0]struction,
    output [31:0] RData1,
    output [31:0] RData2
    );
	reg [31:0] _reg[31:0];
	integer i;
	
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
			if(J==01||bgezal)begin
					_reg[31]<= {16'b0,PC4[15:0]};
					$display("$%d <= %h",5'b11111,{16'b0,PC4[15:0]});
			end
		end
	end
	assign RData1 = _reg[RS1];
	assign RData2 = _reg[RS2];

endmodule
