`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    11:37:33 11/25/2016 
// Design Name: 
// Module Name:    alu 
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
/////////////////////////////////////////////////////////////////////////////////
module alu(
    input [31:0] A,
    input [31:0] B,
    input [2:0] ALUOp,
	 input Clk,
    output [31:0] C,
	 output Zero,
	 output Zero2
    );
	 wire [31:0]DELTA;
//	 always@(Clk)begin
//			$display("A=%h , B=%h",A,B);
//		end
	assign C=(ALUOp==3'b000)?(A+B):((ALUOp==3'b001)?(A-B):((ALUOp==3'b010)?(A&B):((ALUOp==3'b011)?(A|B):((ALUOp==3'b100)?(B>>A[4:0]):~A))));
	assign Zero=(A==B?1:0);
	assign DELTA=A-0;
	assign Zero2=((DELTA[31]==0)?1:0);
endmodule
