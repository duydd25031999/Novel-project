package com.tutorial.novelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.tutorial.novelproject.ui.read.ParagraphViewList;

import java.util.Arrays;
import java.util.List;

public class ReadChapterActivity extends AppCompatActivity {
    public final static String CHAPTER_URL = "chapter_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        setListParagraph();
    }

    private void setListParagraph() {
        LinearLayout listView = findViewById(R.id.paragraph_list);
        List<String> listParagraph = getList();
        ParagraphViewList paragraphViewList = new ParagraphViewList(listView, this);
        paragraphViewList.listView(listParagraph);
    }

    private List<String> getList() {
        String[] paragragh = {
            "2 - Hai kẻ từng là mạnh nhất thế giới hiện cũng đang có mặt ở đó.",
            "3 - -Ah…dễ thương quá…",
            "4 - -Kho báu…Đứa trẻ này mới thực sự là kho báu của thế giới này.",
            "5 - Những lời đó đều dành cho con trai của tôi, Gran-kun.",
            "6 - Và nó đến từ cựu Thiên vương, Granbaza cùng với cựu Anh hùng Arant.",
            "7 - Hai kẻ từng sống chết với nhau trên chiến trường, hai kẻ từng coi nhau như kẻ thù không đội trời chung.",
            "8 - Giờ đều đang không thể rời mắt khỏi sinh linh bé nhỏ đang ngủ trư ngon lành trong nôi.",
            "9 - -Cháu…đây là cháu nội của ta sao…Thật là một sinh linh quý giá, thiêng liêng….",
            "10 - -Một ngày nào đó, ta sẽ được gọi bằng “Ông” sao? Ta hiểu rồi…giờ thì ta hiểu rồi…Tất cả đàn ông trên thế giới này hẳn đều mong muốn sống sót đến ngày có một đứa trẻ gọi mình bằng “Ông”.",
            "11 - Haa….Thần linh ơi.",
            "12 - Nhưng điều quan trọng nhất hiện tại là, hai người họ đang hòa bình với nhau.",
            "13 - Bởi rốt cuộc, họ vốn là kẻ thù.",
            "14 - Cả hai đã giành phần lớn cuộc đời để tiêu diệt kẻ kia, để rồi đánh nhau tới mức trọng thương mà không bên nào đạt được mục đích.",
            "15 - Chỉ nhiêu đó cũng đủ thấy ân oán giữa hai bên đã chất cao đến mức sẵn sàng sống mái mỗi lần đụng độ.",
            "16 - Nhưng theo một nghĩa nào đó, khoảnh khắc này, khi hai kẻ thù truyền kiếp đang ngồi cạnh nhau…",
            "17 - -Ahh…dễ thương quá…",
            "18 - -Ngoan..ngoan…ngoan…",
            "19 - Mọi thù hận, tranh đấu đều như tan biến trước mặt đứa con trai nhỏ của tôi.",
            "20 - Đây là sức mạnh của một đứa trẻ sơ sinh sao?",
            "21 - -Oi, Granbaza.",
            "22 - -Hm? Cái gì?",
            "23 - -Ngươi không phải đang hơi quá thân mật với cháu nội ta sao?",
            "24 - -Ngươi nói cái gì chứ? Đây là con trai của Dariel. Là con trai của đứa trẻ ta đã tự tay nuôi lớn. Nên đứa bé này cũng đâu có khác gì là cháu nội ta?",
            "25 - -Cháu nội ta không quen biết với một tên quỷ tộc như nhà ngươi.!!",
            "26 - -Liên kết của trái tim có thể vượt qua cả quan hệ huyết thống. Ngươi biết tên thằng bé là gì chứ? Là Gran đó. Ngươi cũng hiểu là nó được lấy từ đâu mà?",
            "27 - -Grừ!!!!",
            "28 - -Việc lấy một phần tên ta đặt cho con trai của mình chứng tỏ Dariel luôn kính trọng và coi ta như cha. Thằng bé thật là hiếu thảo.",
            "29 - -Hou…nếu thằng bé chỉ vừa mới chào đời, có vẻ tên vẫn thay đổi được nhỉ? Dariel ! Hãy đổi tên thằng bé thành Aran và vứt quách cái tên Gran đó đi.",
            "30 - Phiền thật đấy…",
            "31 - Con trai của con đang ngủ trưa, nên xin hai người đừng tranh cãi ngay ở bên cạnh nôi như vậy có được không?",
            "32 - Haiz…rồi cứ thế, từ sáng đến giờ, hai người đàn ông lớn tuổi cứ bám riết lấy cái nôi của Gran.",
            "33 - Dù không phải lo chuyện họ sẽ tương tàn với nhau lần nữa, nhưng….",
            "34 - -Gahhh!! Ta mới là ông nội của nhóc Gran. Bọn ta có quan hệ huyết thống trực tiếp đó.",
            "35 - -Tên của thằng bé được lấy từ tên ta. Nhiêu đó đủ chứng tỏ ta mới xứng đáng là ông nội của nó.",
            "36 - Đã nói là hai người đừng có cãi nhau khi ở gần nôi mà…",
            "37 - Gran-kun sẽ thức giấc mất.",
            "38 - Nhưng trước khi tôi định tiến lại can ngăn…",
            "39 - *Choang*",
            "40 - *Choang*",
            "41 - -Ita….",
            "42 - -Cái gì vậy?",
            "43 - -Hai người đừng làm ồn khi con trai của con đang ngủ được không?",
            "44 - Người đã ra tay trước là Marika, với chiếc khay bạc quen thuộc.",
            "45 - Đứng trước hai kẻ từng là mạnh nhất của con người và quỷ tộc, nhưng cô ấy hoàn toàn không có chút kiêng nể nào.",
            "46 - -Ngủ trưa rất quan trọng để em bé có thể phát triển khỏe mạnh. Vì thế nếu cả hai người tiếp tục làm phiền, con buộc phải mời cả hai ra ngoài.",
            "47 - -Ta xin lỗi…",
            "48 - -Ta biết rồi…ta biết rồi, ta sẽ không cãi nhau nữa và giữ im lặng, vì thế đừng đuổi ta ra ngoài, nhé…",
            "49 - Sức mạnh của vợ tôi luôn ở mức đáng sợ như vậy.",
            "50 - Có thể khiến cựu Anh hùng và cựu Thiên vương phải cúi đầu xin lỗi thì không phải dạng vừa rồi.",
            "51 - Dù có cầu xin thế nào, sau khi bị Marika thuyết giảng cho một trận, cả hai ông già đều bị đuổi ra ngoài.",
            "52 - -Dariel này, vợ con đáng sợ thật đó.",
            "53 - -Không hề, ta lại thấy con bé rất nhiệt tình và đảm đang đấy chứ. Tất cả đàn ông đều thích một cô gái có nhiều nét giống mẹ mình. Nó là một bản năng đã ăn sâu vào tiềm thức. Ta có thể thấy được hình bóng mẹ của Dariel ở con bé.",
            "54 - Nhìn vào hai người từng là kẻ thù của nhau giờ đang khoác vai nói chuyện và cười nói, tôi thở dài.",
            "55 - Giờ thì, hãy sắp xếp lại mọi chuyện một chút.",
            "56 - Cựu Thiên vương mạnh nhất trong lịch sử, Granbaza.",
            "57 - Cựu Anh hùng mạnh nhất trong lịch sử, Arant.",
            "58 - Hai người họ từng đứng ở hai đầu chiến tuyến như đại diện cho con người và quỷ tộc.",
            "59 - Chuyện này chắc không cần nhắc lại.",
            "60 - Mỗi người họ đều là những người quan trọng trong cuộc đời tôi vì nhiều lý do.",
            "61 - Trước hết, Cựu Thiên vương Granbaza.",
            "62 - Là sếp cũ của tôi, khi còn là một Hắc Kị sĩ thuộc biên chế của quân đoàn Quỷ. Ông ấy là người đã quan tâm, chăm sóc tôi cả trong công việc lẫn trong cuộc sống.",
            "63 - Không, có lẽ phải nói rằng ông ấy đã nuôi nấng tôi từ khi còn là một đứa trẻ mồ côi, vì vậy cũng không sai nếu tôi xem ông ấy là một người cha.",
            "64 - Nói cách khác là cha nuôi của tôi.",
            "65 - Tiếp đến là Cựu Anh hùng Arant, người từng là đối thủ số một của chúng tôi khi còn ở trong quân đoàn Quỷ.",
            "66 - Có lẽ không dưới hai lần đối mặt trên chiến trường với ông ấy mà tôi cảm thấy tính mạng mình bị đe dọa nghiêm trọng.",
            "67 - Nhưng hơn tất cả, Arant lại là người cha thực sự của tôi.",
            "68 - Khi còn trẻ, ông ấy đã bị thất lạc đứa con trai vừa mới sinh của mình do bị một con quỷ bắt cóc.",
            "69 - Chính ông ấy cũng thừa nhận rằng bản thân chiến đấu là để xóa đi nỗi buồn và hận thù với tộc Quỷ.",
            "70 - Con trai của Anh hùng mất tích, cùng thời điểm ấy, những người có liên quan đến tộc Quỷ đều đã xác nhận lại một loạt những sự kiện và con người có liên quan với chuyện ấy. Và sau khi liên kết tất cả lại, dường như có một tỉ lệ khá cao đứa trẻ năm ấy chính là tôi.",
            "71 - Nói cách khác, tôi có quan hệ huyết thống với Anh hùng mạnh nhất trong lịch sử.",
            "72 - -Woah…",
            "73 - Giờ nghĩ lại tôi mới cảm thấy ngạc nhiên.",
            "74 - Thật trớ trêu khi Cựu Anh hùng đã đem lòng thù hận và tức giận khi bị mất đứa con trai để giáng lên nơi mà đứa con trai của ông ấy đang sống và làm việc.",
            "75 - Và giờ khi sự thật được phơi bày và hai bên tái ngộ, đứa trẻ năm ấy giờ đã trở thành một ông chú trung niên ngoài 30 tuổi.",
            "76 - Và nhờ có sự dễ thương của nhóc Gran, mà trận chiến sinh tử cuối cùng giữa hai kẻ thù không đội trời chung đã bị ngăn cản.",
            "77 - Đối với Arant, trước mặt ông ấy lúc này là con trai, là cháu trai của ông ấy.",
            "78 - Có lẽ, ngay cả trong mơ, cũng chưa bao giờ Arant có thể tưởng tượng được bản thân mình có thể đoàn tụ với đứa con trai đã thất lạc hơn ba mươi năm kia.",
            "79 - Còn với Granbaza, ngay khi biết được đứa trẻ được đặt theo tên của mình, ông ấy cũng đã tỏ ra vô cùng quý mến Gran.",
            "80 - Nhờ đó mà mọi hận thù đã bị xóa bỏ.",
            "81 - -Dariel-san…",
            "82 - Reidi, anh hùng đương nhiệm, kéo tôi ra một góc.",
            "83 - Cô ấy hoàn toàn không có cơ hội làm gì dù cho cả Anh hùng tiền nhiệm và Thiên vương tiền nhiệm đang ở đây.",
            "84 - -Thật đáng sợ. Rốt cuộc anh là một tồn tại đặc biệt…",
            "85 - -Hm?",
            "86 - -Không phải vậy sao? Anh thừa hưởng dòng máu và sức mạnh khủng khiếp của Anh hùng mạnh nhất trong lịch sử, lại còn được nuôi dưỡng và dạy dỗ bởi Thiên vương mạnh nhất trong lịch sử.",
            "87 - Ừm…cái này thì không sai.",
            "88 - Cha đẻ của tôi là Anh hùng mạnh nhất, còn cha nuôi của tôi là Thiên vương mạnh nhất.",
            "89 - Giờ nghĩ lại, tôi cảm thấy hình như mình có hơi quá bất thường…",
            "90 - Đó là tôi sao? Thực sự cái thân phận của tôi đáng nể đến thế sao??",
            "91 - -Đó là lý do vì sao mà Dariel-san lại mạnh mẽ đến lố bịch như vậy. Quả nhiên, anh mới là người xứng đáng với vị trí anh hùng hơn tôi…Có lẽ tôi nên nhường lại vị trí này cho anh.",
            "92 - -Không…không…không…chờ tí đã nào..",
            "93 - Tôi sẽ không trở thành Anh hùng đâu.",
            "94 - Mặc dù đã ra sức từ chối tham gia tổ đội Anh hùng, nhưng giờ tôi còn đang được mời trở thành Anh hùng luôn sao?",
            "95 - Với tôi lúc này, làng Rakus mới là thứ quan trọng hơn.",
            "96 - Để bảo vệ sự bình yên của nơi này, tôi không thể tham gia hành trình chinh phục Quỷ vương. Tôi không muốn rời khỏi đây.",
            "97 - -Tôi không muốn xa gia đình nhỏ của mình….",
            "98 - -Đúng đó, ta đồng ý với con.",
            "99 - Bằng cách nào đó, Arant, anh hùng tiền nhiệm cũng nghe thấy câu chuyện và lên tiếng đồng tình với tôi.",
            "100 - -Gia đình là thứ quan trọng hơn tất cả. Để vợ và con trai của con an toàn, đừng ngần ngại từ bỏ những thứ mình không mong muốn. Dariel này..",
            "101 - -Dạ?",
            "102 - -Hãy yêu thương, chăm sóc và bảo vệ gia đình nhỏ của con hơn bất kì điều gì. KHông có điều gì quan trọng hơn thế cả. Dù đó có là nghĩa vụ của Anh hùng đi nữa. Ta chỉ nhận ra nó khi đã mất tất cả, nhưng con thì không được phép như thế. Hiểu chưa?",
            "103 - -Vâng!!!",
            "104 - Cựu Anh hùng Arant dường như có một cách nghĩ hoàn toàn khác sau khi đoàn tụ với tôi.",
            "105 - Nó khiến tôi thực sự cảm thấy ấn tượng.",
            "106 - Cho đến gần đây, tôi chỉ là một đứa trẻ mồ côi và đã xem việc không có cha mẹ như thứ gì đó đương nhiên.",
            "107 - Những kí ức của tôi về thời thơ ấu bên cạnh cha mẹ hầu như là con số không.",
            "108 - Tôi cứ thế lớn lên, trưởng thành một cách yên bình mà không có chút vấn đề nào, cho đến khi ông ấy xuất hiện.",
            "109 - Mối quan hệ huyết thống bất ngờ này, tôi chưa thể nào chấp nhận nó ngay lập tức. Nhưng cũng không thể nói thẳng với ông ấy, như thế quá tàn nhẫn.",
            "110 - Thật may là chúng tôi vẫn có thể nói chuyện với nhau như thế này.",
            "111 - -Ừm, tốt lắm. Ta không chỉ hạnh phúc vì con còn sống, mà còn hạnh phúc vì con đã trở thành một người đàn ông mạnh mẽ và trưởng thành.",
            "112 - -Vâng ạ.",
            "113 - Vì thế, tạm thời tôi vẫn sẽ giữ những điều đó trong lòng mình.",
            "114 - Và ngôi làng nhỏ của chúng tôi vẫn sẽ tiếp tục bình yên."
        };
        return Arrays.asList(paragragh);
    }
}
