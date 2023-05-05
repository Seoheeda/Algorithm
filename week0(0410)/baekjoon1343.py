board_s = input()

board_m = board_s.replace("XXXX", "AAAA")
board_f = board_m.replace("XX", "BB")
board_test = board_f.replace(".", "").replace("A", "").replace("B", "")

if len(board_s) == 0 or len(board_s) > 50 or len(board_test) > 0:
    print(-1)
else:
    print(board_f)