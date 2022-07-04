def solution(id_list, report, k):
    answer = []
    
    ban_list = [] #정지될 이용자 리스트
    mail_list = [] #받을 메일의 수 리스트
    
    name_list = [] #name_list[0]은 [muzi를 신고한 사람의 이름], [1]은 [frodo를...]
    new_name_list = [] #name_list에서 중복 제거
    
    for j in id_list:
        temp_list = []
        name_list.append(temp_list)
        mail_list.append(0)
    
    for i in report:
        string_list = i.split() #muzi / frodo
        for j in id_list:
            if j == string_list[1]: #신고 받은 사람 기준
                idx = id_list.index(j)
                name_list[idx].append(string_list[0]) #신고한 사람 이름 추가
                break
                
    # name_list = [[apeach], [muzi, apeach], [], [frodo, muzi]]
    
    #중복 제거
    for n in name_list:
        name_set = set(n)
        n_list = list(name_set)
        new_name_list.append(n_list)
        
    
    for n in new_name_list:
        if len(n) >= k:
            ban_list.append(new_name_list.index(n)) #정지 목록에 번호 추가
            
    # ban_list = [1(frodo), 3(neo)]
    
    for b in ban_list:
        for n in new_name_list[b]:
            idx = id_list.index(n)
            mail_list[idx] += 1
        
    #mail_list = [2,1,1,0]
    
    answer = mail_list
    
    return answer