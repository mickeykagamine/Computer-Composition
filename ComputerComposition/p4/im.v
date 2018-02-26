`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:25:40 11/30/2016 
// Design Name: 
// Module Name:    im 
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
module im(
    input [31:0] Addr,
    output [31:0] Out
    );
	reg [31:0] _in[1023:0];
	wire [11:2]Addr1;
	assign Addr1=Addr[11:2];
	assign Out = _in[Addr1];

	initial begin
		$readmemh("code.txt",_in);
	end
	
endmodule
