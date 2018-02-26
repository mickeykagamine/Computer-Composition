`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:57:51 12/07/2016 
// Design Name: 
// Module Name:    MEMWB 
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
module MEMWB(
	input [31:0]ReadDataM,
	input [31:0]ALUOutM,
	input [4:0]WriteRegM,

   input [31:0]PCPlus8M,
	
	input RegWriteM,
	input [1:0]MemtoRegM,
	
	input Clk,
	input Reset,
	
	output RegWriteW,
	output [1:0]MemtoRegW,
	
	output [31:0]PCPlus8W,
	
	output [31:0]ReadDataW,
	output [31:0]ALUOutW,
	output [4:0]WriteRegW
    );
	 
	 initial begin
			readdata<=32'b0;
			aluout<=32'b0;
			writereg<=5'b0;
			regwrite<=0;
			memtoreg<=0;
			
			pcplus8<=32'b0;
	 end
	 
	 reg [31:0]pcplus8;
	 reg [31:0]readdata,aluout;
	 reg [4:0]writereg;
	 reg regwrite;
	 reg [1:0]memtoreg;
	 assign ReadDataW=readdata,
			  ALUOutW=aluout,
			  WriteRegW=writereg,
			  
			  PCPlus8W=pcplus8,
			  
			  RegWriteW=regwrite,
			  MemtoRegW=memtoreg;
	 always @(posedge Clk)begin
		if(Reset)begin
			readdata<=32'b0;
			aluout<=32'b0;
			writereg<=5'b0;
			regwrite<=0;
			memtoreg<=0;
			
			pcplus8<=32'b0;
		end
		else begin
			readdata<=ReadDataM;
			aluout<=ALUOutM;
			writereg<=WriteRegM;
	
         pcplus8<=PCPlus8M;
	
			regwrite<=RegWriteM;
			memtoreg<=MemtoRegM;
		end
	 end


endmodule
