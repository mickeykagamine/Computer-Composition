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
    input signed [31:0] B,
    input [5:0] ALUOp,
	 input [4:0]S,
    output [31:0] C
    );
	 wire [31:0]DELTA;
//	 always@(Clk)begin
//			$display("A=%h , B=%h",A,B);
//		end
	assign C=(ALUOp==6'b000000)?(A+B):
				((ALUOp==6'b000001)?(A-B):
				((ALUOp==6'b000010)?(A&B):
				((ALUOp==6'b000011)?(A|B):
				((ALUOp==6'b000100)?(A^B):
				((ALUOp==6'b000101)?(B<<S):
				((ALUOp==6'b000110)?(B>>S):
				((ALUOp==6'b000111)?(({32{B[31]}}<<(32-S))+(B>>S)):
				((ALUOp==6'b001000)?(B<<16):
				((ALUOp==6'b001001)?(({32{B[31]}}<<(32-A[4:0]))+(B>>A[4:0])):
				((ALUOp==6'b001010)?{{16{B[15]}},B[15:0]}:
				((ALUOp==6'b001011)?B:
				~A)))))))))));
	assign DELTA=A-0;
	assign Zero2=((DELTA[31]==0)?1:0);
endmodule
