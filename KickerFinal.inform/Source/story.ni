"Kicker" by Pippin Barr

Include Basic Screen Effects by Emily Short.
Release along with an interpreter and cover art.

[ DEBUGGING SETUP ]

DebugMode is text that varies. DebugMode is "NO DEBUG".
VerboseMode is text that varies. VerboseMode is "NO VERBOSE".
SimulationMode is text that varies. SimulationMode is "player";
Use no scoring.


Part - Intro

When play begins:	
	say "'Let me hear 'win' on three, 1 2 3…'";
	say "[paragraph break]";
	say "'[bold type]WIN![roman type]'";
	say "[paragraph break]";
	say "It's gameday again. You jog down the tunnel to the field with the coach's inspirational speech still ringing in your ears. By the time you emerge onto the field, the frenzied introductions of the offensive and defensive stars are over and the welcoming rows of cheerleaders have broken up. You quietly wend your way through the men disassembling the giant helmet that the other players got to burst through to the adoration of the crowd.";
	say "[line break]";
	say "You head over to your team's sideline and stand inconspicuously near your practice equipment. One of the backup linebackers runs past and stops to smash his facemask into yours, screaming [italic type]Gameday! It's on now! Wooooooo![roman type] but when he realises who you are he looks disappointed and walks away. You pretend to stretch your kicking leg.";
	pause the game;

IntroText is a truth state that varies. IntroText is false;



Part - Speeches



To say the halftime speech:
	clear the screen;
	say "The team re-groups in the locker room during half time to nurse nagging injuries and, inevitably, to listen to the coach's speech before you all head back out.";
	say "[line break]";
	say "The coach yells for some time about the poor quality of the [one of]running game[or]passing game[or]defense[or]special teams play[or]line play[or]blocking[or]blitz pickup[or]run blocking[or]pass blocking[at random] in the first half. He works himself up into [one of]quite a state[or]quite a lather[or]a furious, incoherent rant[or]an apoplectic rage[at random] and for a moment you worry he's going to [one of]have a hernia[or]burst one of the veins in his neck[or]pop a vein in his forehead[or]have a stroke[at random], but he reins himself in a little. He talks more calmly for a while about the need for [one of]everyone to put in 110%[or]the special teams unit to set the tone for field position[or]the passing game to really turn it on and pick up some big yards[or]the defense to step it up and really dominate[or]the offensive line to punish the defense[at random] throughout the second half. He also singles out [one of]the quarterback[or]the running back[or]the left tackle[or]the right tackle[or]the free safety[or]the right corner[or]the left outside linebacker[at random] for a real reaming, expressing [one of]extreme displeasure[or]disgust[or]contempt[or]enormous disappointment[at random] with various aspects of his performance so far.";
	say "[line break]";
	say "After a lot more screaming, the coach's attention alights on you. He reminds you of how he's never much liked kickers unless they're winning games [if KickingMistakesMade is greater than 0]and that with your bizarre behaviour thus far you are on thin ice with him indeed[otherwise]although you've at least behaved yourself well enough[end if]. [if HFGM is less than HFGA or HXPM is less than HXPA]He works himself up into another bit of a shout, though, when he points out your job is not to [italic type]miss[roman type] kicks, but to [italic type]make[roman type] them, and would you please have a go at that in the second half[otherwise if HFGM is greater than 0 or HXPM is greater than 0]Grudgingly, the coach acknowledges that you've at least made the kicks required of you so far[otherwise]He scowls as he notes the team hasn't needed you for anything so far, and that he hopes you'll be kicking extra points in the second half[end if].";
	say "[line break]";
	say "The coach calls everyone in and as per usual you end up right on the outside of the giant mass of huddled players as the coach shrieks, ''dominate' on three! 1, 2, 3…'";
	say "[paragraph break]";
	say "'[bold type]DOMINATE![roman type]'";
	say "[paragraph break]";
	say "As always, your voice sounds embarrassingly reedy amongst the others. You run out down the tunnel behind the rest of the team to the sidelines.";

	
To say the overtime speech:
	clear the screen;
	say "The team re-groups in the locker room during the overtime break, exhausted but exhilarated by the state of the game. As you all prepare to run back onto the field, the coach launches into his inspirational overtime speech";
	say "[line break]";
	say "He keeps it brief, excoriating only a handful of players and largely trying to be encouraging to everyone else. It doesn't look like it comes easily to him.";
	say "[line break]";
	say "As he winds up, the coach's attention alights on you. He points a baleful finger at your chest and tells you that if you get the chance to win the game you better not screw it up.";
	say "[line break]";
	say "Finally, he calls everyone in and somehow you find yourself in the very center of the players' outstretched arms as the coach roars, ''victory' on three! 1, 2, 3…'";
	say "[paragraph break]";
	say "'[bold type]VICTORY![roman type]'";
	say "[paragraph break]";
	say "As you jog down the tunnel to the sidelines your ears are still ringing.";



Part - Help

Getting help is an action applying to nothing.
Understand "help" and "info" and "information" and "about" as getting help.

GetHelp is a truth state that varies. GetHelp is false.

Carry out getting help:
	Change GetHelp to true;

Every turn:
	if GetHelp is true:
		say "Kicker was born from an idea had in discussion with Doug Wilson and Simon Christiansen. Otherwise it wouldn't exist. It also only works as well as it does because of its testers. A huge thank you, therefore, to Jim and Mary, Doug, Luke, Devin, Chris, Ian, Courtney, and Simon.";
		say "[line break]";
		say "In Kicker you play the role of the kicker for an American football team. That means you run onto the field occasionally to kick the ball to the other team, or to kick it through the goalposts for an extra point after a touchdown or for a field goal. That's the job.";
		say "[line break]";
		say "In this game, as in most interactive fiction, you interact by typing commands that express what you want to do. The available commands tend to be very simple, such as '[bold type]get cup[roman type]' or '[bold type]kick ball[roman type]'.";
		say "[line break]";
		say "When there is a specific, football related command you should type to perform your job as a kicker, the command will appear in [bold type]bold text[roman type] so that you know it's important. You should probably do it immediately.";
		say "[line break]";
		say "Other than that, you could try all kinds of actions, usually by typing a verb and then an object. Some of the actions you can try include [b]look[r]/[b]examine[r], [b]sit[r], [b]stand[r], [b]get[r], [b]drop[r], [b]talk to[r], [b]drink[r], [b]touch[r], [b]taste[r], [b]smell[r], [b]listen to[r], [b]inventory[r], and [b]wait[r].";
		say "[line break]";
		say "You can even just [b]watch the game[r] if you want.";		
		say "[line break]";
		say "You'll figure it out.";
		Change GetHelp to false;
		rule succeeds;

To say b:
	say "[bold type]";
	
To say r:
	say "[roman type]";



Part - Walkthrough

Walkthrough is an action applying to nothing.
Understand "walkthrough" and "walkthru" as walkthrough.

GetWalkthrough is a truth state that varies. GetWalkthrough is false.

Carry out walkthrough:
	Change GetWalkthrough to true;
	
Every turn:
	if GetWalkthrough is true:
		say "Okay, here's the thing.";
		say line break; 
		say "Kicker isn't a game that can be played in the traditional 'text adventure' sense by solving puzzles, scoring points, and making it to the end. Rather, you're just a kicker on a football team and there's a game going on.";
		say "[line break]";
		say "You can still do stuff, such as talking to people, practicing, or drinking sports drink. You can still score points, if you manage to kick an extra point or a field goal. And of course you can win or lose the game, depending on how your team does.";
		say "[line break]";
		say "In short, it's your life to live, kicker.";
		change GetWalkthrough to false;
		rule succeeds;



Part - The World


Chapter - directions you can't go in


First check going rule:
	if the player is on the sidelines:
		if the noun is west:
			say "[one of]You contemplate trying to clamber up into the stands, but figure it's probably not a great idea while the game is going on[or]While it would be a good view from the crowd, you're here to play the game[or]You could go and sit in the crowd, but it seems like you might not be the most popular member of the team if you did that. Not that you're popular now or anything[or]Climb up into the crowd? Perhaps not[at random]." instead;
		otherwise if the noun is north or the noun is south:
			say "[one of]You wander a few steps along the sidelines to get a new perspective. It's actually more or less the same[or]You wander along the sideline a ways and stretch your legs[or]You pace along the sidelines aimlessly[or]You stroll along the sidelines absent-mindedly[at random]." instead;
	otherwise:
		if the noun is east:
			say "[one of]Going over to the opposition's sidelines seems like a very poor idea right now. Maybe after the game[or]Probably better if you don't wander into the opponent's sidelines[or]They don't look especially welcoming on the opposition's sidelines, really, so perhaps not[or]You could go over to the opposition's sideline, but they don't really look like they want you[at random]." instead;
		otherwise if the noun is north or the noun is south:
			say "[one of]You look around and decide that walking along the field just isn't suitable right now[or]Walking up and down the field isn't your job[or]You contemplate going for a walk along the field, but it's not really a good time[or]Rather than walking around on the field, perhaps you should focus on doing your job[at random]." instead;



Chapter - The people

A game figure is a kind of man.
A teammate is a kind of game figure. A teammate is singular-named. 
A teammate group is a kind of game figure. A teammate group is plural-named.
A coach is a kind of game figure.
Coaches are a kind of game figure.
An official is a kind of game figure.

The description of the player is "[if the player is wearing a helmet]Through the bars of your facemask you[otherwise]You[end if] obtain the usual, foreshortened view of your arms, torso and legs. All limbs accounted for.";


An offensive teammate is a kind of teammate.
A defensive teammate is a kind of teammate.
A special teams teammate is a kind of teammate.
A receiver is a kind of offensive teammate.
A runner is a kind of offensive teammate.
An offensive lineman is a kind of offensive teammate.





The odour of a teammate group is usually "You breath in deeply near [the noun]. [one of]Manly stuff[or]The scent of men[or]Sweaty[or]Smells like team spirit[at random].";
The feel of a teammate group is usually "[one of]Always best to keep your hands to yourself when it comes to teammates[or]Hands to yourself, please[or]No touching[or]Nah, they wouldn't like it[at random].";
The sound of a teammate group is usually "[one of]You listen to [the noun] for a couple of seconds but tune out again. Surprisingly boring[or]You listen to [the noun] for a while, but it's all Xs and Os[or]You listen to [the noun] talking amongst themselves about the game, but find it dull and turn away[at random].";
The taste of a teammate group is usually "[one of]You really can't afford to go around licking other members of the team[or]There will likely never be a time when it will be okay for you to lick your teammates[or]Leave them alone, please[at random].";


The odour of a teammate is usually "You breath in deeply near [the noun]. [one of]Manly stuff[or]The scent of a man[or]Sweaty[or]Smells like team spirit[at random].";
The feel of a teammate is usually "[one of]Always best to keep your hands to yourself when it comes to teammates[or]Hands to yourself, please[or]No touching[or]Nah, they wouldn't like it[at random].";
The sound of a teammate is usually "[one of]You listen to [the noun] for a couple of seconds but tune out again. Surprisingly boring[or]You listen to [the noun] for a while, but it's all just Xs and Os to you[or]You listen to [the noun] talking to another player about the game, but find it dull and turn away[at random].";
The taste of a teammate is usually "[one of]You really can't afford to go around licking other members of the team[or]There will likely never be a time when it will be okay for you to lick your teammates[or]Leave them alone, please[at random].";



Section - Offensive unit
		
A teammate group called the offensive unit are in the sidelines. The offensive unit are plural-named.
The description is "[the description of the offensive unit].".
Understand "offense" and "offensive team" and "offensive players" and "offense players" and "offensive player" and "offensive teammate" and "offensive teammates" as the offensive unit.

To say the description of the offensive unit:
	if the offensive unit is in the sidelines:
		say "The offensive unit is sprawled all over the benches they call their own. [one of]The quarterback is telling a joke to the offensive line, who are laughing along[or]The running back is glaring at the tight end of some reason[or]A wide receiver is regaling his teammates with a story about a great catch he made at practice[or]The quarterback is dominating the conversation, as per usual[or]The tight end is explaining some complex looking maneuver to a wide receiver[or]The offensive line looks like it's going to collapse its bench[at random]";
	otherwise:
		say "The offense is standing around on the field. [one of]They look like they'd appreciate it if you left[or]They kind of give you the impression you should leave[or]They don't look too happy about you being out there right now[at random]";

An offensive teammate called the quarterback is a part of the offensive unit. 
The description is "It cannot be denied: he's a good-looking guy.".

A receiver called a wide receiver is a part of the offensive unit. 
The description is "The wide receivers always seem to be standing around giving each other complex handshakes.". 
Understand "wide receivers" and "receivers" as the wide receiver.

A receiver called a tight end is a part of the offensive unit. 
The description is "The tight end perpetually grimaces at some unseen injury.".

A runner called a running back is a part of the offensive unit. 
The description is "The running back shimmies his shoulders and shifts his feet around, clearly hoping someone is watching.".

Some offensive linemen called the offensive line are a part of the offensive unit. The offensive line is plural-named.
The description is "The offensive line are all together and in order as per usual: left tackle, left guard, center, right guard, right tackle all in a row. They create an extreme concentration of mass that might actually have some detectable gravitational pull. They are mountains of men.". 

An offensive lineman called the left guard is a part of the offensive line.
The description is "A Makalu of a man.".

An offensive lineman called the right guard is a part of the offensive line.
The description is "An Everest of a man.".

An offensive lineman called the left tackle is a part of the offensive line.
The description is "A K2 of a man.".

An offensive lineman called the right tackle is a part of the offensive line.
The description is "A Kangchenjunga of a man.".

An offensive lineman called the center is a part of the offensive line.
The description is "A Lhotse of a man.".



Section - Defensive unit

A teammate group called the defensive unit are in the sidelines. The defensive unit is plural-named.
The description is "[the description of the defensive unit].".
Understand "defense" and "defensive team" and "defensive players" and "defensive teammates" and "defense players" as the defensive unit.

To say the description of the defensive unit:
	if the defensive unit is in the sidelines:
		say "The defensive unit is hunched over on their area of benches, looking tense. [one of]You still can't remember a single one of their names or, frankly, their positions[or]They're all about as big and scary as usual[or]They moodily study photographs of play formations and communicate in grunts and the odd word[or]They don't look welcoming[at random]";
	otherwise:
		say "The defense is standing around on the field. [one of]They look like they'd appreciate it if you got off the field[or]They kind of give you the impression you should leave[or]They don't look too happy about you being out on the field right now[at random]";



Section - Special teams unit

A teammate group called the special teams unit are in the field. The special teams unit are plural-named.
The description is "[if the special teams unit is in the sidelines]Your teammates on special teams are clustered around the special teams coach[otherwise]Your teammates are all here[end if]." 
Understand "kicking team" and "kick team" and "kick unit" and "kicking unit" as the special teams unit.
Understand "kick blocking unit" and "extra point unit" and "field goal team" and "coverage unit" and "onside kick team" and "onside kickoff team" and "onside recovery unit" and "onside recovery team" as the special teams unit when the player is on the sidelines.
Understand "kickoff team" and "kickoff unit" and "coverage unit" and "coverage team" and "teammates" and "team" as the special teams unit when KickType is "kickoff" and the player is in the field.
Understand "onside kickoff team" and "onside kickoff unit" and "kickoff team" and "kickoff unit" and "onside team" and "onside unit" and "recovery unit" and "recovery team" and "onside recovery unit" and "onside recovery team" and "coverage unit" and "coverage team" as the special teams unit when KickType is "onside kick" and the player is in the field.
Understand "field goal unit" and "field goal team" as the special teams unit when KickType is "field goal" and the player is in the field.
Understand "extra point unit" and "extra point team" as the special teams unit when KickType is "extra point" and the player is in the field.
Understand "team" and "teammates" and "player" and "players" as the special teams unit when the special teams unit is in the field and the player is in the field.



Section - Generic players

A teammate called your teammate is in the sidelines. He is scenery. He is singular-named.
The description is "He's one of the guys. Unlike you.".
Understand "player" and "teammate" and "man" and "defensive player" and "offensive player" and "special teams player" and "defensive teammate" and "offensive teammate" and "special teams teammate" as your teammate.


A teammate group called your teammates are in the sidelines. They are scenery. They are plural-named.
The description is "It's the guys on the team.".
Understand "players" and "teammates" and "men" as your teammates.


Section - Holder

A special teams teammate called the holder is in the sidelines.
The description is "It's the holder, though he prefers to be known as the backup quarterback. He doesn't much like his holding job and has told you more than once that he finds crouching down so that you can kick the ball degrading.".
Understand "backup quarterback" as the holder.
The odour is "The sour smell of resentment.".
The feel is "The holder looks at you with a look that says, 'Don't touch me.'".
The taste is "If you did lick the holder he would probably taste sour. But you don't lick the holder because that's disgusting.".
The sound is "It's the sound of someone who really doesn't want to speak to you.".


Section - Punter

A special teams teammate called the punter is in the sidelines. 
The description is "[if the punter is in the sidelines]The punter is sitting on his own staring at his shoelaces[otherwise]The punter is standing nervously on the field[end if].".
The odour is "He smells like sadness.".
The feel is "Leave him alone, he has enough problems already.".
The taste is "Let's just imagine he tastes fine and move on.".
The sound is "You can hear the punter muttering something to himself sadly.".


Section - Head coach

A coach called the head coach is in the sidelines.
The description is "[one of]The head coach catches you looking at him and narrows his eyes[or]The head coach shakes his head minutely at you and you look away[or]The head coach is ignoring you, which is standard fare[or]The head coach looks as scary as he usually does. You even gulp nervously on seeing him[or]The head coach is planted on the sidelines like always, unhappy with absolutely everything. Especially you[or]The head coach is doing his best not to notice you. He's good at it[at random].".
The odour is "He smells like anger.";
The feel is "Best not to lay hands on the head coach.";
The taste is "Do you really want to lick the head coach? No, you don't.";
The sound is "He's not talking to you at the moment.";


Section - Special teams coach

A coach called the special teams coach is in the sidelines.
The description is "The special teams coach [one of]nods to you with a big fake encouraging smile[or]waves the little book of zen koans he's always carrying at you[or]gives you an exaggerated thumbs up[or]makes a little prayer gesture and bows to you solemly[or]is in the middle of some kind of minimalist yoga post. It's kind of embarrassing to watch[at random]."
The odour is "He smells like green tea.";
The feel is "Much as he might like a backrub from you right now, it's probably better not to.";
The taste is "He probably wouldn't even mind, but you shouldn't anyway.";
The sound is "What is the sound of the special teams coach not talking?";

A book of zen koans is carried by the special teams coach. 
The description is "It's the special teams coach's book of zen koans. He always has it with him.".


Section - Assistant coaches

Some assistant coaches are coaches in the sidelines.
The description is "The various assistant coaches scurry around you so fast you can't even make out their faces. Except for the special teams coach, of course, who always has his eye on you."
The odour is "They're moving too fast to give off a consistent scent!";
The feel is "You reach out to touch one of them, but he's already gone.";
The taste is "Restrain yourself, please.";
The sound is "They sound like the rushing of the wind.";
Understand "assistants" and "coaches" and "assistant" and "assistant coach" as the assistant coaches.


Section - Film crew

A film crew is a person in the sidelines.
The description is "The film crew [one of]frantically wave at you to get out of their shot[or]are filming the quarterback. What a surprise[or]are looking around for someone to interview. It won't be you, though[or]stalk around the sidelines looking for dramatic stories[or]are complaining about the quality of the light[at random].".
Understand "cameraman" and "sound man" and "grip" and "director" and "reporter" as the film crew.
The odour is "They smell like the fame you'll never have.";
The feel is "Just leave them alone, they're busy.";
The taste is "Really? No.";
The sound is "You hear the sound of movie magic being made.";


Section - Cheerleaders

Some cheerleaders are women in the sidelines.
The description is "The cheerleaders [one of]are doing high kicks and dance routines. You've seen this routine many times[or]are in the middle of a complex sequence of pompom twirls[or]smile brightly into the air[or]beam into the crowd, shaking their pom poms above their heads[at random].".
Understand "women" as the cheerleaders.
The odour is "They smell like cheerleaders. They don't look too appreciative of being sniffed.";
The feel is "No touching!";
The taste is "Control yourself.";
The sound is "You hear the sound of distilled pep.";


Section - Officials

The odour of an official is usually "How about you quit trying to sniff the officials and play the game?";
The feel of an official is usually "Leave the officials alone and do your job.";
The taste of an official is usually "Instead of licking [the noun], how about you play the game?";
The sound of an official is usually "[The noun] will only talk to you if something goes wrong, so how about you make sure nothing goes wrong?";
The description of an official is usually "[the description of an official].".

Some officials called some officials are in the field.
The description is "They sure do look great in stripes. The referee is the only official near enough to you to actually see particularly well, though.".

The referee is an official in the field. The referee is scenery.
Understand "official" and "ref" as the referee.

Does the player mean climbing something:
	it is unlikely;
	
To say the description of an official:
	say "[one of]He looks great in stripes[or]He looks quite serious at the moment[or]He looks like he's taking the game very seriously[or]He looks like he's just waiting to give a penalty[or]His hand hovers near the yellow flag in his pocket, itching to call a penalty[at random]";

The refereeoffthefield is an official in the sidelines. The refereeoffthefield is scenery. The printed name is "referee".
Understand "referee" and "official" and "ref" as the refereeoffthefield when the player is on the sidelines.
The odour is "You cannot and should not smell the referee from all the way over on the sidelines.".
The taste is "You stick your tongue out toward the referee, but it turns out your tongue is not quite long enough for the job.".
The sound is "He's a very quiet man, except when he's calling penalties.".



Section - Opposition

Some people called the opposition is in The field.
The description is "[one of]They look like they want to win[or]They look like they mean business[or]They look pretty tough[or]They look big and mean[or]They look like you should keep your distance[at random].".
Understand "away team" and "opponent" as the opposition.
The odour is "Smelling the opposition is not part of your relationship with them.";
The feel is "Touching the opposition is not part of your relationship with them unless they're smashing you into the turf, which you don't want.";
The taste is "Licking the opposition is outside your job description and you should keep it that way.";
The sound is "They sound like they mean business.";




Section - The crowd

The crowd is a backdrop in the sidelines and in the field.
The description is "[a crowd description]".
The odour is "The crowd doesn't smell like anything from where you are and it's best you keep it that way.";
The feel is "They're too far away. Also: stop trying to touch the crowd.";
The taste is "If you could taste them they would probably taste like beer, but they're too far away to be absolutely sure.";
The sound is "They sound like a big crowd at a football game.";


To say a crowd description:
	if the player is on the sidelines:
		if the table of crowd descriptions is not empty:
			Choose a random row in the table of crowd descriptions;
			Say "[desc entry]";
			blank out the whole row;
		otherwise:
			say "[one of]Nothing much to see now[or]You feel as though you've seen it all now[or]You get the sense there's nothing to see in the crowd anymore[or]There's nothing interesting up there[or]You don't see anything special[at random].";
	otherwise:
		Say "You squint, but the crowd is just a bunch of writhing colours from here.";

Table of Crowd Descriptions
desc
"A man with a giant foam hand reading 'We're #1' catches your eye for a moment before he merges back into the roiling crowd."
"You sight three shirtless men holding a giant D and then a white picket fence. They look happy and drunk."
"You catch a glance of a young boy at the game with his father eating a hotdog the same size as his arm."
"You see a row of at least eight or nine men with their faces painted in your team's colours. They're all using their cellphones to take photos of each other and themselves."
"Right at the back of the stands you seem to see the figure of death, with cowl, scythe and all. Was that fancy dress or your imagination or what?"
"You get a glimpse of a young woman looking about as bored as you have ever seen anyone look in your life."
"You seem to make direct eye contact with an angry looking man with a huge, bushy beard. He shakes his fist at you."
"Your eyes settle briefly on an elderly couple who are kitted out with rugs, thermoses and every other sign of old age at a ball game. You feel a stab of envy."
"You think you see your brother up there in the cheap seats. But is he cheering or just screaming?"
"A group of rowdy-looking bros with a beer in each hand seem to salute you with their beverages."
"You see a man jumping up and down in a jersey with your number on it and are momentarily elated. Then you remember that you have the same number as a quarterback from the 60s and that the jersey is a retro jersey. Your self-esteem returns to normal levels."
"You seem to see an entire row in the stands occupied by girl scouts. You blink and look for them again, but you can't find them."
"You make prolonged eye contact with an old woman wearing a jersey with the opposing running back's number. She's quite intimidating, but you stay strong. Eventually she looks away."
"You see a guy clad only in a g-string shaped like a football helmet. How did he even get in?"
"You catch sight of a man or woman in a gorilla suit wearing your team's helmet. Everyone in the nearby seats seem to be spending more time giving the gorilla high-fives than paying attention to the game"
"You see a man in a neon yellow wig staring obsessively up at the jumbotron, presumably waiting to see himself. He has his camera ready for the big moment."
"You see two young women holding a sign that says 'We can't believe we're on TV!!!', but they aren't on TV"
"Four or five kids punching each other repeatedly in the face with giant foam 'We're #1' hands."
"You see a man in a trucker cap carefully making a circular shape out of paper. He suddenly launches it into the air and it floats a long way above the crowd before diving down."
"You watch as a plastic bag floats like a jelly-fish above the crowd's heads, swooping and dipping. You wonder if that was beautiful or not."
"While you watch a group of young men break into a complex-looking dance that you think is meant to spell out that name of your team."
"You observe a man a feverishly devouring a pretzel. He's so hairy it takes you a moment to realise he's not wearing a shirt."
"You see a small boy with binoculars observing the field with great intensity. A man next to him, presumably his father, tries to show him the giant foam hand he's just bought, but the boy waves him away."
"You see two men dressed in opposing team colours sitting frostily next to each other, each with his body turned slightly away from the other."
"Your eyes fall on an empty seat, the only one in the stadium for all you know. You feel a chill pass through you and shiver slightly."
"You have the dubious pleasure of witnessing a wiry man fit an entire pretzel into his mouth in a single, fluid movement. He sits with his cheeks bulging, looking very pleased with himself."
"You find yourself gazing at an elderly man who appears to be intently knitting an image of the scene on the field he sees before him, constantly looking up to check the accuracy of the portrayal."
"You see a woman dressed as a can of beer and a man dressed as a woman clearly enjoying themselves very much. The people behind them are less pleased with the height of the can constume and the man's beehive wig."
"As you watch, a man engrossed in a newspaper has it suddenly blown out of his hands. It floats away into the sky, separating out to look like a flock of birds The man follows it mournfully with his eyes."
"You see a man eating a peach in an obscene manner as he seems to lock eyes with you. You look down hurriedly."
"You see an old man in the opposition team's colours readying himself to throw a battery into your sidelines. You flinch involuntarily as he makes the throw, but it's a feeble effort, travelling all of four rows and hitting a guy wearing one of those beer hats."
"You find yourself watching a woman who has a cat sitting on her shoulder. The cat is wearing a tiny version of your team's helmet and is watching the game with great intensity while the woman talks on her cellphone."
"You try to single someone out in the crowd but your eyes blur and it's just a mass of writhing shapes and colours. Just like life itself, right?"
"While you look on, a man in a suit in the front row buys a beer from a vendor and then tosses back over his shoulder, drenching the man behind him. They are instantly walled off by a mass of bodies chanting, you assume, 'fight! fight! fight!'"
"You see a man in a suit rolling and unrolling his tie while he looks at the action on the field. It's not clear he knows he's doing it and the woman sitting next to him looks disturbed by the ritual."
"You observe a woman in a plastic-looking feather headdress trying to lead the crowd behind her in a complex sequence of dance moves. One slightly chunky guy has a go for a while, but gives up when he sees no one else is getting involved."
"You see a small boy holding a sign up that reads 'GET A SACK'. The specificity and lameness of the message somehow warms your heart."
"You see a beer vendor trip on a pretzel and perform an impressive and graceful sequence of dance-like moves to maintain his balance and save his tray of beers. Those sitting nearby leap to their feet in a standing ovation."
"You watch a man returning to his seat suddenly realise he hasn't zipped his fly since going to the bathroom. He zips it up as subtly as he can and looks pleased with himself that nobody around him saw."
"A generic fan in the quarterback's jersey with a foam 'We're #1' hand and a beer."
"You see your father. [paragraph break]Wait… did you? [paragraph break]No, can't have."
"A little girl sleeping on her mother's lap while her mother pats her head and drinks a beer from a paper cup."
"You see what appears to be a motorcycle gang filling up almost an entire row of seats, a black leather line drawn on the canvas of the crowd."
"You see a bald, shirtless man painted entirely mauve. Which is a little odd given that mauve isn't one of team colours for either team playing."
"You see a small child pointing rapturously up into the sky, but when you look up you can't see anything at all."
"You catch a glimpse of twin men with handlebar moustaches repeatedly high-fiving each other. It looks like they're never going to stop. Ever."
"You see a woman going through the choreography of one of the dances of the swans from Swan Lake. It's quite cramped in the seating area, however, so she's struggling to be really expressive."
"You see a teenaged boy who is managing to sit in his seat upside down, his shoulders and craned neck resting on the seat while his spine arches over the seatback. He's doing his damndest to seem casual about it, while also clearly hoping to impress."
"You see a group of people who have managed to sit such that they can hold up red placards and make the shape of an 8-bit heart."
"You see a few people rise listlessly from their seats with limply flapping arms as the world's weakest wave passes through their section."







Chapter - The Sidelines

Section - Deciding where the player is

To decide whether the player is on the sidelines:
	if the player is not in the field:
		yes;
	otherwise:
		no;

To decide whether the player is on the field:
	if the player is in the field:
		yes;
	otherwise:
		no;



Section - The room
	
The Sidelines is a room. The printed name is "The Sidelines".
The description is "Ah yes, the sidelines. The place where you spend almost all of your time. The ground is a little muddy and the benches are almost vindictively uncomfortable, but it is what it is. There's a bunch of equipment around and as always it's crowded with teammates, coaches, and assorted others. To the west you can see the part of the stadium seating nearest you, and to the east you can see the field. The Jumbotron looms above it all.[the introductory text]".
The view of the sidelines is "It's your sidelines. You can see assorted coaches and teammates over there, milling around and watching the game".


to say the introductory text:
	if IntroText is false:
		say "[paragraph break]";
		say "Before you know it your team has won the coin toss and elected to kick off. That would be your job. Now would be a good time to [b]run onto the field[r].";
		say "[line break]";
		say "(If you need help during this game, try [bold type]help[roman type] or [b]walkthrough[r].)";
		now IntroText is true;

Section - The game

A thing called the game is in the sidelines. The game is scenery.
The odour is "Smells like… [if HomeScore > AwayScore]victory[otherwise if HomeScore < AwayScore]defeat[otherwise]a draw[end if].".
The feel is "It feels like surprisingly little when you're not in the middle of it.".
The taste is "You'd like a taste of the action, it's true, but you're not on the field so it'll have to wait.".
The sound is "The game sounds mostly like violent collisions with the occasional announcement from the referee.".

Instead of examining the game:
	if Player is on the field:
		Say "You're [italic type]in[roman type] the game, you don't need to [italic type]watch[roman type] it right now!" instead;
	otherwise if KickStatus is "get on the field":
		Say "Rather than watch the game, perhaps you should get into it." instead;
	otherwise if PlayCalled is "player field goal":
		Say "Rather than watch the game, perhaps you should get into it." instead;
	otherwise:
		Change WatchTheGame to "true";
	

A thing called the fieldgame is in the field. The fieldgame is scenery. The printed name is "game".
The odour is "Smells like… [if HomeScore > AwayScore]victory[otherwise if HomeScore < AwayScore]defeat[otherwise]a draw[end if].".
The feel is "It feels like surprisingly little when you're not in the middle of it.".
The taste is "You'd like a taste of the action, it's true, but you're not on the field so it'll have to wait.".
The sound is "The game sounds mostly like violent collisions with the occasional announcement from the referee.".
Understand "game" as fieldgame when the player is on the field;

Does the player mean examining the fieldgame:
	yes;
	
Instead of examining the fieldgame:
	say "Yes, this is the game and you're in it.";


Chapter - Watching the game

WatchTheGame is a text that varies. WatchTheGame is "false";
CheckTheScoreboard is a text that varies. CheckTheScoreboard is "false";

[Watching the game is an action applying to nothing.
Understand "watch" and "watch the game" and "watch game" and "look at game" and "look game" and "x game" and "examine game" as watching the game.
Understand "x field" and "x the field" and "look field" and "look at field" and "look at the field" and "watch field" and "watch the field" as watching the game when the player is on the sidelines.

Check watching the game:
		
		
Carry out watching the game:
	Change WatchTheGame to "true";
]

Section - The jumbotron

A thing called the SidelinesJumbotron is in the sidelines. The SidelinesJumbotron is scenery. The printed name is "Jumbotron".
The description is "You look up at the enormous screen suspended above the stands and see the game going on. Of course, you could also just watch the game directly anyway, since you're so close to it.".
Understand "jumbotron" and "screen" as the SidelinesJumbotron when the player is on the sidelines.

A thing called the FieldJumbotron is in the field. The FieldJumbotron is scenery. The printed name is "Jumbotron".
The description is "You look up at the enormous screen suspended above the stands and see the game going on. Hey, it's you up there!".
Understand "jumbotron" and "screen" as the FieldJumbotron when the player is on the field.


Instead of examining the SidelinesJumbotron:
	if KickStatus is not "get on the field":
		say "[one of]You look up at the giant screen suspended above the stands[or]You glance up at the Jumbotron[or]You stare up at the enormous screen[or]You cast your gaze up at the Jumbotron[or]You check the Jumbotron[at random].";
		Change WatchTheGame to "true";
	otherwise:
		say "[one of]You look up at the Jumbotron to see a still shot of you standing around on the sidelines when you should be getting onto the field to kick[or]On the Jumbotron there's an image of a kicker standing around on the sidelines. What a jerk[or]You see live footage of yourself not getting on the field like you should be[or]It's you. You not doing your job[or]Turns out there's a giant image of you standing around staring at the Jumbotron on the Jumbotron[at random].";
	
Instead of examining the FieldJumbotron:
	if KickStatus is "pending signal" or KickStatus is "pending kick":
		say "[one of]You see a nicely framed shot of yourself not kicking the football. Coach would be so proud[or]It's you, not kicking[or]You see a live feed of your failure to do the one thing you're on the team to do[or]You see the home team kicker not doing his job[or]Up on the giant screen is a giant you doing a giant amount of nothing[at random].";
	otherwise if KickStatus is "post kick":
		say "[one of]You see a shot of yourself standing around when you should be leaving the field of play. It looks kind of embarrassing[or]The video on the Jumbotron looks a lot like you standing out on the field when you should be on the sidelines[or]It's you. You not getting off the field in a timely manner[or]You, and the entire stadium, are treated to a live feed of you not getting of the field[or]The Jumbotron shows a giant image of you standing around looking at the Jumbotron instead of getting off the field of play[at random].";
	otherwise if KickStatus is "kickoff tackle":
		say "You see an artfully frame shot of you looking at the Jumbotron instead of tackling the kick returner.";
	otherwise:
		say "Maybe you should look at the Jumbotron some other time.";
	


Section - The items

A thing called the equipment is in the sidelines. The equipment is scenery. The printed name is "equipment".
The description is "Mostly obviously, your own practice equipment is set off to one side, well away from any of the other players. You like to think it's so that your teammates don't disturb your laser-like focus, but you fear it's the other way around. There are quite a few technological devices around the place too, but you don't really know what they're for. Obviously there are large containers of sports drink available on the drinks table, should your delicate constitution become dehydrated, and some large free-standing fans constantly blowing a cool mist at anyone who wants it."
Understand "equipment" as the equipment.
The odour is "Mmm, equipment.";
The feel is "[The noun] feels like you thought it would.";
The taste is "You contemplate licking every piece of equipment on the sidelines, but find yourself voting against it.";
The sound is "It sounds like nothing.";

The equipmentmanager is a man in the sidelines. The printed name is "equipment manager".
The description is "A ratty little guy always roaming the sidelines in search of dissatisfication.".
Understand "equipment manager" and "manager" as the equipmentmanager.
The odour is "He smells like he works too hard.";
The feel is "Leave him be.";
The taste is "Licking the equipment manager is pretty close to the thing you want to do least in this world.";
The sound is "He's muttering something about jockstraps. Again.";


Does the player mean examining the equipmentmanager:
	it is unlikely;
	
Does the player mean taking the equipmentmanager:
	it is unlikely;
	
Does the player mean doing something with the equipmentmanager:
	it is unlikely;
	
Some technological devices are part of the equipment.
The description is "Well, you don't know what they're for so you can't really tell what you're looking at. There are some devices with screens and some that look like radio transmitters.".
The odour is "Mmm, tastes like technology.";
The feel is "[The noun] feel like you thought they would.";
The taste is "You contemplate licking every piece of technology on the sidelines, but find yourself voting against it.";
The sound is "They sounds like nothing much.";

Understand "turn on" as switching on;
Understand "turn off" as switching off;

Instead of switching on the technological devices:
	say "[don't touch].";

Instead of switching on the technological devices:
	say "[don't touch].";

Instead of switching on the radio transmitters:
	say "[don't touch].";

Instead of switching off the radio transmitters:
	say "[don't touch].";

Instead of switching on the screens:
	say "[don't touch].";

Instead of switching off the screens:
	say "[don't touch].";

To say don't touch:
	say "[one of]Best not to touch, really[or]Every fibre of your being tells you to leave the technology stuff alone[or]Last time you tried to touch one of the devices on the sidelines you got in surprisingly big trouble, you probably shouldn't do it again[or]Given that you don't know how to use any of this stuff, maybe you should jsut leave it alone[or]You reach toward [the noun], but then think better of it and try to mind your own business[at random]";

Some screens are part of the equipment.
The description is "On the screens you can see various views of the game. An assistant coach runs by and tells you not to touch anything, his voice rising and falling with the doppler effect."
Understand "screen" as the screens.
The odour is "Mmm, screeny.";
The feel is "[The noun] feel like you thought they would.";
The taste is "Leave licking screens to other, more mentally disturbed men.";
The sound is "The screens aren't making any sound.";

Some radio transmitters are part of the equipment.
The description is "Now that's a lot of switches and dials. An assistant coach runs by and tells you not to touch anything, his voice rising and falling with the doppler effect."
The odour is "Is that the smell of radio waves you detect? Probably not.";
The feel is "[The noun] feel like you might imagine.";
The taste is "Maybe you have better things to do than licking a radio?";
The sound is "You hear a faint static hiss.";

Some fans are part of the equipment.
The description is "You reflect that these may be only fans you'll ever have. Cool and refreshing, though."
Understand "fan" as the fans.
The odour is "The mist coming off the fans smells like nothing, which is vaguely disappointing.";
The feel is "You contemplate sticking your finger into the spinning blades, but refrain.";
The taste is "Didn't your mother ever tell you not to lick fans? Well don't.";
The sound is "It sounds like whirring.";


Section - Practising

The practice equipment is part of the equipment.
The description is "Your faithful kicking net and practice holder stand at the ready as always for you to get in some quality practice kicking. A practice ball is already set up in the holder."
Understand "practise equipment" and "practise" and "practice" as the practice equipment.
The odour is "Somehow [the noun] actually smells like practice.";
The feel is "[The noun] feel like you knew they would.";
The taste is "Somehow [the noun] actually tastes like practice.";
The sound is "You can't hear anything from [the noun].";

The kicking net is a part of the practice equipment.
The description is "It's a little net that allows you to kick the ball without it flying off into the crowd or hitting someone important in the head. Pretty handy!".
Understand "net" as the kicking net.
The odour is "It has a netty aroma.";
The feel is "It looks and feels like a kicking net.";
The taste is "Mmm, netty.";
The sound is "You can't hear anything from [the noun].";

A thing called the practice holder is part of the practice equipment.
The description is "It's a little plastic contraption that leans over a football to support it on its end. It's just like the how the backup quarterback holds the ball for you on the field, except the backup quarterback doesn't like spending time with you, so you have this plastic thing instead.".
Understand "holder" as the practice holder when the player is on the sidelines.
The odour is "It has no aroma.";
The feel is "Feels like plastic.";
The taste is "Mmm, plastic.";
The sound is "You can't hear anything from [the noun].";

A ball is a kind of thing.

The practice ball is part of the practice equipment.
The description is "A regulation kicking football that you can practice kicking into the net."
Understand "ball" as the practice ball when the player is on the sidelines.
The odour is "It smells like leather. Because it's made of leather.";
The feel is "It feels like a ball.";
The taste is "Leathery.";
The sound is "[The noun] doesn't seem to be making any noise, oddly enough.";


Practising kicking is an action applying to nothing.
[Understand "practise kicking" and "practise kicking" and "practice" and "practise" and "kick ball" and "kick" and "practise kick" and "practice kick" as practising kicking when the player is on the sidelines.
]




Section - Sports drink

A drinks table is part of the equipment.
The description is "A table with some oversized sports drink containers on it along with some paper cups.".
The odour is "It smells like sports drink.";
The feel is "Yep, feels like a table.";
The taste is "There's really no earthly need to lick [the noun].";
The sound is "It's not making any noise.";

Some drinks containers are on the drinks table. They are scenery.
The description is "A nice orange vat of orange sports drink."
The odour is "They smell like sports drink.";
The feel is "They feel solid enough.";
The taste is "Or how about you get a paper cup and get a drink the normal way instead?";
The sound is "The containers aren't making any noise.";

Some sports drink is in the drinks containers.
The description is "Delicious, tangy sports drink. Tastes like orange. The colour.".
The odour is "Smells like orange. The colour.".
The feel is "It feels wet. And now nobody else wants to drink it. Great job.".
The taste is "Why don't you just drink it from a cup like a normal person?".
The sound is "Sounds like orange. The colour. Wait, what?".

Some cups are part of the equipment.
The description is "They are definitely paper cups."
Understand "paper cups" as the cups.
The odour is "They smell like paper.";
The feel is "Papery.";
The taste is "They're for drinking sports drink out of, not for licking.";
The sound is "They're not making any noise.";

The thing called a paper cup is on the drinks table. It is scenery. A cup can be empty or full. It is empty. 
The description is "[if the cup is empty]An empty paper cup[otherwise]A paper cup of orange sports drink[end if].".
The odour is "[if the cup is empty]It smells like paper[otherwise]It smells orange[end if].";
The feel is "It feels like a paper cup.";
The taste is "[if the cup is not empty]Tastes like orange. Maybe you should drink it[otherwise][The noun] tastes papery[end if].";
The sound is "It's not making any noise.";

Instead of taking a cup:
	if the player has a cup:
		say "[one of]One is all you need[or]You have a paper cup[or]You've already got a cup[or]One cup is enough[or]Two cups would be wasteful[or]Another cup would be surplus to requirements[or]No, one is enough[or]You don't need another cup[or]How many paper cups do you need? Just one[at random]." instead;
	if the player is on the bench:
		surreptitiously move the player to the location;
		now the player is standing;
		say "(First getting off the bench.)";
	say "You take a fresh cup from the stack.";
	move the cup to the player;


Filling is an action applying to nothing.
Understand "fill cup" and "fill the cup" and "put sports drink in cup" and "put drink in cup" and "put drink into cup" and "put sports drink into cup" and "get sports drink" and "take sports drink" and "get drink" and "take drink" and "pour sports drink into the cup" and "pour drink in cup" and "pour drink" and "pour a drink" and "get drink" and "pour sports drink in cup" and "fill the cup with sports drink" as filling.

Check filling:
	if the player does not have the cup then say "You don't have a cup." instead;
	if the cup is full then say "Calm down, there's already sports drink in the cup." instead;
	if the player is on the bench:
		surreptitiously move the player to the location;
		now the player is standing;
		say "(First getting off the bench.)";
	Say "You fill the cup with orange liquid.";
		
	
Carry out filling:
	Now the cup is full;
	


A person can be wet or dry. A person is usually dry.
DrinksHad is a number which varies. DrinksHad is 0.


Understand the command "drink" as something new.
Drinking sports drink is an action applying to nothing.
Understand "drink" and "drink cup" and "drink from cup" and "drink sports drink" and "drink orange" and "drink liquid" and "drink orange liquid" as drinking sports drink.

Check drinking sports drink:
	if the player does not have the cup then say "You don't have a cup." instead;
	if the cup is empty then say "There's nothing to drink in the cup." instead;
	if the player is wearing the helmet then say "You contemplate trying to splash the drink through your facemask and into your mouth, but end up deciding not to." instead;

Carry out drinking sports drink:
	if DrinksHad is 5:
		increase DrinksHad by 1;
		say "[one of]You've had rather a lot of sports drink already. It's probably not a good idea[or]You know, you're kind of at your limit so maybe you shouldn't[or]Again? You've already had a lot, and another might be too much[at random]. [if the player is wet][one of]Especially given your track record[or]And things have gone wrong in the past after all[or]This could get ugly, you know[at random].[end if]" instead;
	otherwise if DrinksHad is 6:
		Change DrinksHad to 0;
		say "[one of]Well, that was the sports drink that broke your bladder's back[or]Great, that was one too many for you[or]And there we have it, the consequences of drinking too much sports drink on the sidelines[at random]. [if the player is wet]Again. [end if][one of]You quietly wet yourself, then drop the cup discretely in the trash[or]You wet your pants and quietly drop the now empty cup into the trash[or]You pee your pants and throw the paper cup in the trash[or]You blush as you wet yourself, then drop the paper cup into the trash[or]You look around as you wet your pants, but nobody saw. You drop the cup in the trash[at random].";
		Now the player is wet;		
	otherwise:
		Say "[if the player is wet][one of]What the hell, right? A man's got to keep hydrated[or]You remember wetting yourself earlier, but that was then[or]Even though your pants are still damp from your little accident earlier, you're thirsty damnit[or]You did wet yourself earlier, but screw it[at random]. [end if][one of]You throw back the sports drink and go Aaaaaahh[or]You drink down the paper cup of sports drink thirstily[or]You gulp the sports drink down[or]You drink the sports drink[or]You slam back the sports drink[or]You drink down the sports drink[at random], [one of]then crumple the cup in a minor show of strength before throwing into the garbage[or]and toss the cup into the trash[or]then shoot the cup into the trash like a basketball[or]then scrunch the cup in your hand and toss it into the trash[at random].";
	Now the cup is empty;
	Move the cup to the drinks table;
	Increase DrinksHad by 1;
	

Instead of dropping the cup:
	say "[one of]You toss the paper cup into the trash, because you're tidy like that[or]You put the cup into the trash to keep the sidelines nice and tidy[or]You deposit the cup into the trash, rather than just dropping it on the ground[at random].";
	move the cup to the drinks table;
	now the cup is empty;
	


Section - Sitting (and standing) on the benches

The bench is scenery in the sidelines. The bench is an enterable supporter.
The description is "It's very hard to understand why the benches have to be quite [italic type]so[roman type] unpleasant to sit on, but they are.".
Understand "benches" and "seat" and "seats" as the bench.
The odour is "How about we pretend you didn't just try to sniff the bench?";
The feel is "Harder than a rock, somehow.";
The taste is "Let's just pretend you didn't try that.";
The sound is "It's not making any noise.";

Posture is a kind of value. The postures are seated and standing.
A person has a posture. The posture of a person is usually standing.

Understand the command "sit" as something new.
Sitting is an action with past participle sat, applying to one touchable thing.
Understand "sit on [something]" as sitting.
Understand "sit" and "sit down" as sitting.

Rule for supplying a missing noun when sitting (this is the missing noun rule):
	let count be the number of enterable supporters in the location;
	if count is 0:
		Say "[one of]You think about sitting down on the field, but decide it would be too undignified[or]You could sit down on the field, it's true. But you don't[or]Sit down on the field? Not today[or]Instead of sitting yourself down on the field, you remain standing[or]You can't bring yourself to just sit down on the field[at random]." instead;
	otherwise if the count is greater than 1:
		Say "On what?" instead;
	otherwise:
		let target be a random enterable supporter in the location;
		change the noun to the target;
		Say "(On [the target].)";
		
Check an actor sitting (this is the sitting twice rule):
	if the actor is seated:
		Say "[one of]You're already sitting on it[or]You're already sitting down[or]You try sitting down [italic type]even more[roman type] than you already were, but it's just not possible[or]You focus on your posture on the bench[or]You try to achieve an even greater degree of sittingness[or]You do your best to be a model sittizen[at random]." instead;

Check an actor sitting (this is the sitting on a non-supporter rule):
	if the noun is not an enterable supporter:
		if the noun is a person:
			Say "[one of]That seems like a pretty terrible idea[or]You size up [the noun] as a seat, but can't see an appropriate surface[or]You ponder sitting on [the noun], but the mental image is just too horrifying[or]You make a small movement to sit on [the noun] but he pull out of it before they notice[or]You really, really don't want to sit on [the noun][at random]." instead;
		otherwise if the noun is a supporter:
			Say "[one of]That's not for sitting[or]That's not a seat[or]You can't sit on that, you just can't[or]That's just not appropriate[at random]." instead;
		otherwise:
			Say "[one of]Sitting on [the noun] would be inane[or]You think about sitting on [the noun], but recognize in your heart that it would be a stupid thing to do[or]You contemplate [the noun] as something to sit on, but it simply is not[or]That would be foolish[or]That would look weird, so you don't[or]A full-grown man sitting on [the noun]? Perhaps not[at random]." instead;

Carry out an actor sitting:
	surreptitiously move the actor to the noun;
	now the actor is seated;
	
Report an actor sitting:
	Say "You find an empty spot on the bench and sit down. [one of]None of your neighbours look very happy about it[or]The player you sit down next to looks vaguely embarrassed that you're there[or]Sure is uncomfortable[or]It's about as uncomfortable as a bench can reasonably be[or]The players to your left and right rotate their shoulders away from you[or]The players on either side of you fold their arms, their elbows digging into your sides[or]The player next to you gestures at you with his thumb and says something inaudible to the guy next to him. They laugh[at random].";


Understand the command "stand" as something new.
Standing is an action applying to one touchable thing.
Understand "stand on [something]" as standing.
Understand "stand" and "stand up" and "get up" and "get off" as standing.

Every turn:
	if the player is not on the bench:
		Now the player is standing;

Rule for supplying a missing noun when standing (this is the missing noun for standing rule):
	if the player is seated:
		Surreptitiously move the player to the location;
		Now the player is standing;
		Say "[one of]You get up off the bench[or]You stand up from the bench[or]You rise to your feet from the bench[or]You stand up[or]You stand[at random]." instead;
	otherwise:
		Say "[one of]You're already standing up, but you experimentally try standing up [italic type]even more[roman type]. It doesn't work out[or]You stand up straighter[or]You pull your shoulders back and stand up nice and straight[or]You imagine you're sitting down and then imagine standing up. Feels good[or]You stand up even straighter than you had previously thought possible[at random]." instead;
		
Check an actor standing (this is the standing twice rule):
	if the actor is on the noun and the actor is standing:
		Say "[one of]You're already standing on it[or]You are, in fact, already standing on the bench. No one really knows why[or]You already are[or]You've already standing on the bench like a madman[at random]." instead;

Check an actor standing (this is the standing on a non-supporter rule):
	if the noun is not an enterable supporter:
		if the noun is a person:
			Say "[one of]That seems like a pretty terrible idea[or]Yeah… no[or]You size up [the noun] as a place to stand and decide it doesn't look good[or]You probably could manage to stand on [the noun], but it's just not going to happen today[or]Instead of standing on [the noun], you instead do not stand on [the noun][at random]." instead;
		otherwise if the noun is a supporter:
			Say "[one of]That's not for standing on[or]You can't stand on that[or]That's not going to happen[or]You might be able to, but you don't[at random]." instead;
		otherwise:
			Say "[one of]You can't stand on [the noun][or]Standing on [the noun] would be absurd[or]No, you may not[or]You don't stand on [the noun], you just don't[or]No can do[at random]." instead;
	
Carry out an actor standing:
	surreptitiously move the actor to the noun;
	now the actor is standing;
	
Report an actor standing:
	Say "[one of]Channeling the Dead Poet's Society you stand up on the bench[or]You stand up on the bench, almost losing your balance as your cleats slip a little[or]You get up onto the bench[or]You clamber up onto the bench[or]You step up onto the bench[or]Feeling a little foolish, you step up onto the bench[at random]. [one of]Everyone conscientiously ignores you[or]Nice view from up here actually[or]Everything around you looks a little bit lower now[or]Sometimes a change of perspective can be refreshing[or]You feel a little unstable[or]The player you're now towering above gets up off the bench and walks away without comment[at random].";
	


Section - Player reactions to sitting and standing

Every turn:
	if the player is seated:
		let rand be a random number between 0 and 100;
		if rand is less than 20:
			surreptitiously move the player to the location;
			now the player is standing;
			say "[one of]A player comes over and informs you you're sitting in his spot. You get up, but he just walks off[or]The head coach catches you sitting down and shouts something about practice. You stand up[or]A player tells you he wouldn't sit in that spot if he were you. You ask why, but he doesn't say anything. You get up[at random].";
Every turn: 
	if the player is standing and the player is on the bench:
		let rand be a random number between 0 and 100;
		if rand is less than 20:
			surreptitiously move the player to the location;
			now the player is standing;
			say "[one of][Any player] stares at you standing on the bench and you briefly hope he'll say [italic type]O Captain my Captain![roman type], but he just shakes his head and looks away. You get off, embarrassed[or]The equipment manager shrieks at you to stop marking up the bench with your cleat. You step down[or][Any player] walks past you and sneers on seeing you standing on the bench. You get down, trying to look nonchalant[at random].";
			
To say any player:
	Say "[one of]A [one of]backup[or][at random] linebacker[or]A [one of]backup[or][at random] running back[or][one of]backup[or][at random] safety[or]The equipment manager[or]An assistant coach[or]A sideline official[or]A [one of]backup[or][at random] wide-receiver[or]A [one of]backup[or][at random] tight end[or]The backup quarterback[at random]";
	




Section - Player general reactions

Every turn:
	let rand be a random number between 0 and 100;
	if rand is less than 8 and the player is on the sidelines and the table of player general reactions is not empty:
		choose a random row from the table of player general reactions;
		if the offensive unit is in the sidelines:
			say "[One of the offensive unit] [the reaction entry].";
		otherwise:
			say "[One of the defensive unit] [the reaction entry].";
		blank out the whole row;


Table of Player General Reactions
reaction
"smirks at you"
"glances at you, then looks away and shakes his head at a teammate who chuckles"
"snickers your way"
"seems to mutter under his breath about your fitness for your job"
"sees you [if the player is standing]standing[otherwise]sitting[end if] there and rolls his eyes"
"almost seems as if he's going to strike up a conversation with you, but then notices who you are and keeps walking"
"looks your way and frowns immediately"
"walks past and bumps into you a little too hard for it to have been an accident"
"looks at you and sneers"
"grimaces and looks away when you catch his eye briefly"
"goes out of his way not to come within talking distance as he moves past you on the sidelines"
"laughs loudly nearby"



Chapter - The Field

Section - The grass and the ground

A thing called the grass is in the sidelines and in the field. It is a backdrop.
The description is "[if the player is in the field]Now that's some well-kept grass[otherwise]The grass along the sidelines is kind of mangy. Looks nice out on the field, though[end if].".
The odour is "[if the player is in the field]Smells like nice fresh grass[otherwise]The sideline grass smells mostly of mud and dirt[end if].";
The feel is "[if the player is in the field]Springy[otherwise]You don't really want to touch the sideline grass[end if].";
The taste is "[if the player is in the field]You contemplate eating a piece of grass, but maybe you can do that after the game is over instead[otherwise]Best not to[end if].";
The sound is "[if the player is in the field]The grass is silent[otherwise]The grass is silent[end if].";
Understand "ground" and "field" as the grass when the player is in the field.

A thing called the ground is in the sidelines. It is a backdrop.
The description is "It's the ground. It's good at its job.".
The odour is "Smells like ground.";
The feel is "No need to worry, it's solid.";
The taste is "You could lick the ground, but the better option it probably not to.";
The sound is "It's soundless ground.";
Understand "floor" as the ground.


Section - Sidelines views

A thing called the home sidelines is in the sidelines and in the field. It is a backdrop.
The description is "Over on the sidelines you can see the rest of your team, the coaches, and so on.".
The odour is "You can smell it perfectly well once you're off the field.";
The feel is "You know what the sidelines feel like already. All too well.";
The taste is "You can try licking the sidelines once you're off the field.";
The sound is "It's hard not to feel like they're not all talking behind your back.";
Understand "home sidelines" and "sideline" and "sidelines" as the home sidelines.
	
A thing called the opposition sidelines is in the sidelines and in the field. It is a backdrop.
The description is "Over on the opposition sidelines you can see a kind of mirror view of your own team. Except they all hate you and want you to lose.".
The odour is "[The noun] is too far away to smell. Probably a good thing.";
The feel is "Going over to prod at [the noun] is not the best idea right now.";
The taste is "[The noun] is too far away to taste and it would be unwise to move closer for that purpose.";
The sound is "You can't hear much from [the noun] from where you are.";
Understand "opposition sideline" and "sideline" and "sidelines" as the opposition sidelines.

Instead of doing anything with the home sidelines when the player is on the sidelines:
	say "The sidelines are pretty big, perhaps you can be more specific than that.";


A room has a text called view.


Section - the head coach

HeadCoachFromTheField is a thing in the field. The printed name is "the head coach". He is scenery.
Understand "coach" and "head coach" as HeadCoachFromTheField when the player is on the field.
The description is "He's pretty far away, but you can still see he has his angry face on.".
The odour is "[The head coach] is too far away to smell, you're not exactly a bloodhound.";
The feel is "He's too far away.";
The taste is "[The head coach] is too far away to taste.";
The sound is "The sound of the coach's almost constant, incandescent rage wafts to you on the breeze.";

Instead of talking to HeadCoachFromTheField:
	say "Few things are more certain than that the head coach doesn't want you shouting over to him on the sidelines right now.";

To say The head coach:
	say "The head coach";
	

Section - the actual field

The Field is a room. It is east of the the sidelines. The printed name is "The Field".
The description of the field is "You find yourself standing on the field of play at [the player's field location]. [Being on the field]. [The crowd noise]. To the west you can see your home sidelines and to the east is the opposition sidelines. The Jumbotron looms above it all.".
The view of the field is "It's the field. You know? Where the game is played".



To say Being on the field:
	if KickStatus is "pending signal" or KickStatus is "pending kick":
		say "[one of]For the moment you are the center of attention[or]Refreshingly, everyone is paying attention to you[or]It's one of those rare times when all eyes are turned on you[or]All eyes are on you[or]Everyone's watching you[at random]";
	otherwise:
		say "[one of]At the moment people are mostly not so interested in you, however[or]Nobody's really paying much attention though[or]Nobody seems to care much[or]That's about it[at random]";

To say the player's field location:
	if KickType is "kickoff" or KickType is "onside kick":
		say "your own 30 yard line";
	otherwise if KickType is "extra point":
		say "the opposition's 2 yard line";
	otherwise if KickType is "field goal":
		say "the opposition's [100 - PrePlayFieldPosition + 7] yard line";

To say The crowd noise:
	if KickStatus is "pending signal":
		say "The crowd looms all around you, largely silent as they wait for you to signal your kick";
	otherwise if KickStatus is "pending kick":
		say "The crowd looms all around you, emitting a low roar in anticipation of your kick";
	otherwise if KickStatus is "post kick":
		say "The crowd serves as a backdrop made of a writhing mass of colours";


Section - the ball on the field

The kicking ball is scenery in the field.
The description is "[the description of the kicking ball]."
The odour is "It looks like it smells leathery, but now is not the time to find out.";
The feel is "It looks like real leather. Let's assume it is and move on.";
The taste is "People might worry if you were to lick [the noun] right now.";
The sound is "It's not making any noise you can discern.";
Understand "ball" as the kicking ball when the player is on the field;
Understand "game" as the fieldgame when the player is on the field;

Does the player mean examining the kicking ball:
	it is very unlikely;

To say the description of the kicking ball:
	if KickStatus is not "post kick":
		say "It's one of the special 'kicking balls' they use when you come on the field. Sometimes it feels a bit insulting, like you're not even allowed to touch the proper game ball";
	otherwise:
		say "It's a proper game ball, brought onto the field for the proper players to play the proper game";	
		
Does the player mean kicking the kicking ball:
	it is very likely;




Part - Other actions

Section - quitting

Understand the command "quit" as something new.

Quitting is an action applying to nothing.
Understand "quit" and "exit" as quitting.
Understand "leave" as quitting when the player is on the sidelines;

QuitCount is a number that varies. QuitCount is 0.

Carry out quitting:
	Increase QuitCount by 1;
	if QuitCount is 3:
		End the game saying "You quit your job, quitter. You scored [score] points in [turn count] turns.";
	
Report quitting:
	if QuitCount is 1:
		say "You look longingly toward the tunnel leading to the locker rooms, but maybe you could retire honourably at the end of the season instead?";
	otherwise if QuitCount is 2:
		say "You look towards the locker rooms again, but manage to convince yourself not to walk out while the game is still running.";


Section - looking in a direction

Understand "look [direction]" as facing.
Understand "look at [direction]" as facing.
Understand "look to [direction]" as facing.
Understand "examine [direction]" as facing.
Facing is an action applying to one visible thing.

Carry out facing: 
	let the viewed item be the room noun from the location; 
	if the viewed item is not a room: 
		if the noun is west and the player is on the sidelines:
			say "[a crowd description]." instead;
		otherwise if (the noun is north or the the noun is south) and the player is on the sidelines:
			say "It's sidelines all the way along. Well, until they stop, obviously." instead;
		otherwise if the noun is east and the player is on the field:
			say "[the description of the opposition sidelines][line break]" instead;
		otherwise if (the noun is north or the noun is south) and the player is on the field:
			say "It's the field, stretching as far as you can see. Well, 100 yards anyway." instead;
		otherwise if the noun is up:
			say "You're dazzled by the stadium lights and have to look down again quickly." instead;
		otherwise if the noun is down:
			say "You check to see if the ground still exists. It does." instead;
		otherwise:
			say "Nothing much to look at that way, really." instead;		
	try looking toward the viewed item.

Looking toward is an action applying to one visible thing.
Understand "look toward [any adjacent room]" as looking toward.
Understand "examine [any adjacent room]" as looking toward.

Carry out looking toward:
	say "[the view].".
	

Section - waving

The block waving hands rule is not listed in any rulebook.

Instead of waving hands:
	say "[one of]You wave cheerfully to anyone watching, but fail to detect a response[or]You wave and then, having waved, stop[or]You wave enthusiastically, perhaps hoping to see yourself on the Jumbotron[or]You wave at no one in particular and no one in particular waves back[or]You wave and feel a little more cheerful for it[or]You wave your hand for a while, then stop[or]You wave[at random].";

Understand "wave to [something]" and "wave at [something]" as waving;

Instead of waving someone:
	say "You wave to [the noun][one of]You do not receive a courtesy wave in return[or]It goes unreturned[or]No wave comes back[or]Nothing happens[or]You detect no sign of interest[at random].";
	
Instead of waving something:
	say "You wave to [the noun]. [one of]No response[or]Nothing happens[or]Amazingly, there is no wave in return[or]It doesn't seem like [the noun] care[s][or][The noun] wave[s] back! No, wait, [if the noun is plural-named]they don't[otherwise]it doesn't[end if][at random].";
	
Instead of waving the crowd:
	say "You wave to the crowd. [one of]It's hard to tell, but it looks like not a single person waved in return[or]The crowd, being an enormous collection of different people, does not wave back[or]If you were hoping to start The Wave, you failed[or]Was that a wave in return?! No[or]Nothing doing[or]Sadly, the crowd doesn't wave back[or]They do not begin to chant your name[at random].";


Section - clapping

Clapping is an action applying to nothing.
Understand "clap" and "applaud" as clapping.

Report clapping:
	say "[one of]You applaud enthusiastically[or]You clap[or]You put your hands together repeatedly, making a repetitive slapping noise with them[or]You try clapping, and succeed. Clapping sounds ensue[or]You applaud, though only you know for what[at random].";


Section - cheer

Cheering is an action applying to nothing.
Understand "cheer" as cheering.

Report cheering:
	say "[one of]You let out a surprisingly loud [italic type]Woooooo![roman type], impressing yourself[or]You cheer with great vigour, though for what reason only you know[or]You give a loud cheer, startling one or two players nearby[or]You shout your encouragement to your team, though they show no particular sign of appreciating it[or]You cheer loud and long. That felt pretty good[at random].";
	

Section - reading

Understand the command "read" as something new.
Reading is an action applying to one thing.
Understand "read [something]" as reading.

Instead of reading the book of zen koans:
	say "[one of]That doesn't belong to you and you don't have it anyway[or]It's closed and being held by the special teams coach, so it's pretty hard to read right now[or]While it might well be nice to read a book right now, the special teams coach is holding the book of koans and doesn't like other people touching it[or]You can't really read it, given that the special teams coach always has it in his hand[or]The special teams coach is holding it closed, so it's a bit tough to read[at random].";

Instead of reading something:
	say "You start reading [the noun], then wonder about how much that makes sense and stop.";

Instead of reading someone:
	say "You've never been very good at reading people.";
	
Instead of taking the book of zen koans:
	say "[one of]That belongs to the special teams coach. It wouldn't be very zen of you to just take it[or]That's not yours[or]Leave that alone, it isn't yours to take[or]Hey, stealing isn't part of the Noble Eightfold Path[or]How about you don't take what isn't yours[at random].";
	
Instead of opening the book of zen koans:
	say "[one of]It's not yours, leave it alone[or]You reach toward the book of zen koans, but the special teams coach jerks it away[or]Given that the special teams coach is holding the book, it would be pretty rude to start leafing through it right now[or]You don't actually have the book to open[or]Why would you just reach over and fool around with the special teams coach's book? That's not polite[at random].";
	
Instead of reading the screens:
	say "Not much to see, really.";

Instead of reading the SidelinesJumbotron:
	say "Fascinating stuff.";	

Instead of reading the FieldJumbotron:
	say "Fascinating stuff.";


Section - the senses



[ SMELLING ]

The block smelling rule is not listed in the check smelling rulebook.

Instead of smelling a room:
	say "You sniff the air inquisitively, but it turns out you don't have a sensitive enough nose to discern anything informative.";
	
The last carry out smelling rule:
   say "[if the odour of the noun is not empty][the odour of the noun][paragraph break][otherwise]You smell… nothing.[end if]".

A thing has some text called odour. 


[ LISTENING ]
	
The block listening rule is not listed in the check listening to rules.

Instead of listening to a room:
	say "You cock your head to one side and listen really hard. Sounds like sounds.";

The last carry out listening to rule:
   say "[if the sound of the noun is not empty][the sound of the noun][paragraph break][otherwise]Sounds like... nothing much.[end if]".

A thing has some text called sound.

[ TASTING ]

The block tasting rule is not listed in the check tasting rules.

Rule for supplying a missing noun while tasting (this is the ambient tasting rule):
	now the noun is nothing;

Instead of tasting the player when the player is wearing a helmet:
	say "With your helmet on, all you can do is lick your lips. Salty.";

Instead of tasting the helmet when the player is wearing a helmet:
	say "You stick your tongue out, but can't reach any surface of the helmet to taste it. Perhaps if you took it off.";

Instead of tasting something when the player is wearing a helmet:
	say "How do you propose to taste [the noun] while you're wearing your helmet, exactly?";
	
Check tasting:
	if the noun is nothing:
		say "Nothing.";

Carry out tasting:
   say "[if the taste of the noun is not empty][the taste of the noun][paragraph break][otherwise]You don't taste anything very interesting.[end if]".

A thing has some text called taste.


[ TOUCHING ]

Instead of touching something:
	say "[if the feel of the noun is not empty][the feel of the noun][paragraph break][otherwise]Hey! Look but don't touch![end if]".

A thing has some text called feel.

The odour of the player is "All things considered, you smell okay.".
The sound of the player is "By holding very still you can just faintly hear that you sound like nothing.".
The taste of the player is "You lick the back of your hand. Mildly salty.".
The feel of the player is "Are you feeling okay?".


Section - taking

Instead of taking someone singular-named:
	say "[taking someone].";
	
Instead of taking someone plural-named:
	say "[taking someone].";

To say taking someone:
	say "[one of][The noun] vehemently object[s] and you quickly refrain[or]You reach toward [the noun], but somehow it just doesn't seem like a great idea[or]You consider what it would mean to 'take' [the noun] and decide not to instead[or]To take [the noun] or not to take [the noun]… not to take [the noun][or]Instead, you don't even try to take [the noun][at random]";

Instead of taking the player:
	say "Don't worry, you may not have much, but you do have yourself.";

Instead of taking something singular-named:
	say "[one of]Leave it there, there's no good reason to take [the noun][or]Nah[or]You eye [the noun] for a while, but decide to leave it where it is[or]In the end, you don't take [the noun], because you shouldn't[or]Instead of that, you leave [the noun] alone[at random].";

Instead of taking something plural-named:
	say "[one of]Leave them there, there's no good reason to take [the noun][or]Nah[or]You eye [the noun] for a while, but decide to leave them where they are[or]In the end, you don't take [the noun], because you shouldn't[or]Instead of that, you leave [the noun] alone[at random].";


Section - eating

Before eating the player:
	say "You can do that on your own time.";
	rule succeeds;

Before eating someone:
	say "Resorting to cannibalism already? Try to calm down.";
	rule succeeds;

Before eating something:
	say "Keep your unconventional dietary preferences to yourself, please.";
	rule succeeds;
	
Before eating the sports drink:
	say "Generally speaking, we try to [italic type]drink[r] liquids.";
	rule succeeds;


Section - jumping

Instead of jumping:
	say "[one of]You jump up and down a few times, trying to make it look like some sort of calisthenics[or]Boing… boing… boing[or]You jump around, jump around. You jump up, jump up, and get down[or]You manage a pretty good vertical leap and look around to see if anyone is impressed. No one is[or]You jump and briefly gain a different perspective on the world[at random].";




Section - miscellania


Stretching is an action applying to nothing.
Understand "stretch" as stretching.

Report stretching:
	say "[one of]You ostentatiously perform a hamstring stretch, looking around to see if anyone's watching[or]You swing your leg back and forth a bit, trying to look professional[or]You stretch your right calf for a while, then lose interest and stop[or]You do some deep knee bends, but stop when a teammate looks at you as if you're embarrassing yourself[or]You do some brisk lunges and feel vaguely more like a professional athlete[at random].";
	
Jogging is an action applying to nothing.
Understand "jog" as jogging.

Report jogging:
	say "[one of]You jog furiously on the spot for a couple of seconds, then stop. It's tiring[or]You jog along the sidelines for a while, then feel out of breath and stop[or]You go for a little jog in a circle[or]You jog and, having jogged, cease jogging[or]You jog on the spot idly, trying to look active and ready for anything[or]You jog on the spot. Someone snickers[or]You go for a little jog to convince yourself you're a professional athelete[or]You jog yourself a little more awake[at random].";


Exercising is an action applying to nothing.
Understand "exercise" and "do exercise" and "get some exercise" and "do some exercise" and "do exercises" as exercising.

Report exercising:
	say "[one of]You vigorously swing your kicking leg around and feel kind of virtuous[or]You jump and down a few times to get your blood flowing[or]You contemplate dropping to the ground and doing some pushups, but decide against it right now[or]You do a couple of slow one-leg squats on your kicking leg to give your teammates some indication of just how strong you are[or]You pretend to execute a long-distance field goal, swinging your leg violent through its range of motion and looking around to see who's watching[at random].";

Sighing is an action applying to nothing.
Understand "sigh" as sighing.

Report sighing:
	say "[one of]You sigh. It doesn't help your mood[or]Ay, me[or]You let out a heartfelt sigh, but don't get any sympathy[or]You sigh loudly, but no one asks you how you're doing[or]You give a world weary sigh. Ah yes, life[at random].";
	

Instead of attacking:
	say "Yes, you're a 'kicker', but please try to restrain yourself.";
	

Instead of singing:
	say "[one of]You clear your throat to sing a little song, but lose your nerve[or]You sing briefly under your breathe, but the whole thing is simply too embarrassing to continue[or]You think about starting a nice singalong with your teammates, but somehow the mood is all wrong[or]You're a kicker, not a singer[or]You find yourself singing a nursery rhyme from your childhood. Someone snickers[at random].";
	

Spitting is an action applying to nothing.
Understand "spit" as spitting.

Report spitting:
	if the player is wearing the helmet:
		say "[one of]You spit. Mission accomplished[or]You spit and watch as it hits your facemask and dangles there a while before slipping off[or]You spit cleanly through the bars of your facemask and feel a little proud of yourself. Simple pleasures[or]You spit onto the field, feeling manly and transgressive[or]You spit. Good job[at random].";
	otherwise:
		say "[one of]You spit on the ground. Classy[or]You spit. You spat[or]You spit the contents of your mouth on the ground[or]You spit a couple of feet in front of you. Someone somewhere snickers[or]You wind up and spit firmly at the ground. That'll teach it[at random]";
	

Praying is an action applying to nothing.
Understand "pray" as praying.

Report praying:
	say "[one of]You say a short prayer[or]Amen to that[or]A nearby player nods with vague sympathy[or]You mutter a prayer under your breath for protection against the multitude of things you need protection from. Like the opposition and your head coach[or]You say a little prayer for you[at random].";
	

Crying is an action applying to nothing.
Understand "cry" and "sob" and "weep" as crying.

Report crying:
	say "[one of]There may be no crying in baseball, but it turns out there [italic type]is[r] crying in football. You feel a little better[or]A single tear rolls down your cheek. It's very poignant[or]You let yourself cry just a little bit, shielding your face so nobody sees[or]You weep, and you weep alone[or]You sob quietly to yourself[at random].";




Part - Talking

Chapter - the command

Understand the commands "ask" and "tell" and "say" and "answer" as something new.

Understand "ask [text]" or "tell [text]" or "answer [text]" or "say [text]" as a mistake ("[talk to instead]").

Instead of asking someone to try doing something: 
	say "[talk to instead][paragraph break]";

Instead of answering someone that something: 
	say "[talk to instead][paragraph break]";
	
To say talk to instead:
	say "(Use [bold type]talk to[roman type] instead.)";
	
Understand "talk to/with [something]" as talking to. 
Understand "chat to/with [something]" as talking to.
Understand "speak to/with [something]" as talking to.
Understand "converse with [something]" as talking to.

Talking to is an action applying to one visible thing.



Chapter - individuals without much special talking

Section - to the player

Instead of talking to the player:
	Say "[one of]You can't think of what to say[or]You start to speak, but you keep interrupting yourself[or]You've heard it all before[or]You're already enough of an outcast on the team as it is[or]There's nothing left to say[or]You already know what you were going to say, so you don't bother[at random].";
	
Section - to the crowd

Instead of talking to the crowd:
	if the table of crowd responses is not empty:
		choose a random row from the table of crowd responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		say "The crowd is a lot more interested in the game than in you. Give it up.";
		
Table of Crowd responses
response
"You start talking to the crowd as a whole, but stop when you are forced to accept that they simply cannot hear you"
"You can't quite think of something appropriate to say to 75,000 people at once"
"They all look really busy watching the field"
"Somehow it just doesn't feel like they're all going to listen to you"
"Your voice isn't quite [italic type]that[roman type] powerful"
"You clear your throat, but then can't think of anything important enough to say"
"You tell yourself you have better things to do than shouting at the crowd"
"You turn to stare into the seething mass of the crowd and can't think of anything to say"
"You work yourself up to shout something at the crowd, but you lose your nerve"		
"No one would hear you"


Section - to the film crew

Instead of talking to the film crew:
	if the table of film crew responses is not empty:
		choose a random row from the table of film crew responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		say "The film crew is never going to be interested in you. Learn to cope with it.";
		
Table of Film crew responses
response
"The sound guy makes desperate cutting motions across his neck at you and you decide to keep quiet"
"The boom operator glares at you as you clear your throat to speak and you wisely remain silent"
"You approach the film crew to strike up a conversation, but the cameraman swings his camera away such that they end up with their backs to you"
"You start to speak, but the boom operator hisses at you in an alarming manner"
"You try to casually have a word with the film crew, but they all shake their heads at you in unison and you stop"
"They look really busy right now, it would be rude to interrupt them"
"The film crew is in the middle of filming a segment with the sideline reporter, so it's probably not a good time to talk with them"
"You step toward the film crew to have a chat, but they're in the middle of filming a segment about your team's quarterback so you wander away"
"The boom operator shakes his head in eerie slow motion as you open your mouth to speak, so you stop and turn away"
"The sideline reporter narrows her eyes at you and you find you cannot speak"


Section - to the cheerleaders

Instead of talking to the cheerleaders:
	say something to the cheerleaders;
	if the table of cheerleader responses is not empty:
		choose a random row from the table of cheerleader responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		say "the cheerleaders have better things to do that talk to you.";
		
To say something to the cheerleaders:
	if the table of cheerleader queries is not empty:
		choose a random row from the table of cheerleader queries;
		say "[the query entry], but ";
		blank out the whole row;
	otherwise:
		say "You walk over to the cheerleaders and clear your throat to speak, but ";

Table of Cheerleader queries
query
"You edge over to where the cheerleaders are dancing and manage to say something about how you really appreciate their artform"
"You clear your throat and try to impress the cheerleaders by telling them that you used to do ballet when you were a boy"
"You stand near the cheerleaders and commend their performance, putting on your most confident voice"
"You try to sing an impromptu cheer to the cheerleaders"
"You sidle over to the cheerleaders and talk loudly about how people don't realise how import cheering is for the performance of the team"
"You step over to the cheerleaders and try to say something about the similarities between cheering and football"
"You approach the cheerleaders and ask half-jokingly whether any of them want to get coffee and pie after the game"
"You ask the cheerleaders how their day is going"
"You casually remark to the cheerleaders that this game could come down to your kicking in the end"
"You wander over to the cheerleaders and try to engage them in conversation about the weather"

Table of Cheerleader responses
response
"not a single one of them shows the slightest indication that they heard you"
"somehow all the next dance moves they perform involve them having their backs to you"
"they ignore you with every bit as much coordination as their dance routines have. It's quite impressive"
"they're in the middle of an extended cheering routine and ignore you utterly"
"as ever they have no time for you whatsoever"
"they don't even look at you. You tell yourself it's just because they're busy, but it's not"
"not one cheerleader appears to hear you"
"they just go on dancing and smiling their dazzling smiles at the crowd"
"a high-kick comes suspiciously close to catching your chin and you back away"
"they spin away at just that moment and head for a different location"


Section - the the opposition

Instead of talking to the opposition:
	if the table of opposition responses is not empty:
		choose a random row from the table of opposition responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		say "You know what? They just really don't look like they want to chat.";
		
Table of Opposition responses
response
"Coach told you to never talk to the opposition players because you'd just get in trouble"
"They really don't look inclined to chat with you right now, they're playing a game of football"
"They're already looking for an excuse to crush you on a kickoff return sometime, there's no need to give them any more reason by talking to them"
"Now is just not a good time. Maybe after the game. Or, perhaps never, actually"
"Given that nobody on your own team generally wants to speak with you, talking to the opposing team seems a little over-optimistic"
"You should probably try to focus more on playing your part in the game than on your conversational skills"
"You're the team's [italic type]kicker[roman type], not its talker. Leave the opposition alone"
"The away team isn't really dying to talk to you, so perhaps you should just keep it to yourself"
"Instead of chatting with the opposition, how about you just do your job instead"
"Every single one of the opposition players looks like they could crush your helmet with their bare hands, so you should probably leave them alone"
	

Section - to the equipment manager

Instead of talking to the equipmentmanager:
	let rand be a random number between 0 and 100;
	if the player is wet and rand is less than 33 and the table of equipment manager wet responses is not empty:
		choose a random row from the table of equipment manager wet responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		choose a random row from the table of equipment manager responses;
		say "[the response entry].";

Table of Equipment manager responses
response
"The equipment manager [one of]mutters[or]murmurs[or]rasps[or]grunts[at random] something about needing to [one of]check on some[or]clean some[or]prepare some[or]locate some[or]re-check some[at random] [one of]mouth guards[or]cleats[or]jockstraps[or]helmets[or]chin-straps[or]gloves[or]towels[at random] and moves off"

Table of Equipment manager wet responses
response
"The equipment manager stares incredulously at your soiled pants. 'You know my people have to clean those for you, right?' He shakes his head slowly"
"The equipment manager glares at your wet pants and is overtaken by a silent rage. He stalks away"
"The equipment manager points wordlessly at your soiled pants"
"The equipment manager is rendered speechless by your soiled pants, which he and his crew will have to clean after the game"
"The equipment manager is unable to get past the fact you wet your pants. 'Those are team equipment, you know,' he says"

	

Section - to the assistant coaches

Instead of talking to the assistant coaches:
	if the table of assistant coaches responses is not empty:
		choose a random row from the table of assistant coaches responses;
		say "[the response entry].";
		blank out the whole row;
	otherwise:
		say "You try to get hold of one of the assistant coaches, but they just move past so fast!";
		
Table of Assistant coaches responses
response
"You get hold of [one of the assistant coaches], but as his role has nothing much to do with you, you're forced to admit you have nothing useful to say to him"
"[One of the assistant coaches] stops briefly to listen to you before realising you're irrelevant to his job. He moves on"
"You can't quite manage to attract any of the assistant coaches['] attention. They're just so [italic type]busy[roman type]"
"You try to speak with [one of the assistant coaches], but it's like you come from different planets and you quickly give up"
"[One of the assistant coaches] is almost fooled into talking with you, but then reads your jersey number and realises you're just the kicker"
"You're amazed by just how difficult it is to talk to one of these guys. They go flying past you like occupied taxis"
"You're just about to start a conversation with [one of the assistant coaches] when he's called away to do something more important"
"[One of the assistant coaches] falters briefly in his mad dash along the sidelines and almost speaks with you, but then thinks better of it and hurries on"
"You wave both hands to attract the attention of [one of the assistant coaches] but it's as if he doesn't see you and soon he's disappeared into a thicket of players"
"Not a single one of the assistant coaches seems to have a moment to spare"
	
To say one of the assistant coaches:
	say "[one of]one of the assistant coaches[or]the assistant coach you think may be in charge of [an assistant coaching role][or]a random assistant coach[or]someone you think is an assistant coach[or]a guy who at least looks like an assistant coach[or]a guy you believe looks after [an assistant coaching role][at random]";

To say One of the assistant coaches:
	say "[one of the assistant coaches]" in sentence case;
	
To say an assistant coaching role:
	say "[one of]corner technique[or]linebacker drills[or]leadership roles[or]tackling dummies[or]towel use[or]offensive linemen weight lifting[or]weights[or]cardio[at random]";
	




Chapter - Teammates

Section - the logic

Instead of talking to a singular-named teammate:
	say "[the teammate query][the teammate response]";

Instead of talking to a plural-named teammate group:
	say "[the teammate query][the teammate response]";


To say the teammate query:
	if the table of teammate queries is not empty:
		choose a random row from the table of teammate queries;
		say "[The query entry]";
		blank out the whole row;
	otherwise:
		say "You approach [the noun] and start talking about the first thing that comes to mind: [one of]fishing[or]kicking[or]yourself[or]the weather[or]birds[or]the leather industry[or]buses[or]travel[or]existentialism[or]instant replay[or]the rules of snooker[at random]"


To say the teammate response:
	if the player is cowardly or the player is the goat:
		if the player is cowardly:
			if the table of teammate cowardly responses is not empty:
				choose a random row from the table of teammate cowardly responses;
				say "[the response entry]";
				blank out the whole row;
			otherwise:
				say ". Nobody wants to talk to a coward";		
		otherwise if the player is the goat:
			if the table of teammate goat responses is not empty:
				choose a random row from the table of teammate goat responses;
				say "[the response entry]";
				blank out the whole row;
			otherwise:
				say ". You are pointedly ignored";
		let rand be a random number between 0 and 100;
		if the player is wet and rand is less than 33:
			if the table of teammate wet addenda is not empty:
				choose a random row from the table of teammate wet addenda;
				say "[the addendum entry]";
				blank out the whole row;
		otherwise if the player is worrying and rand is less than 50:
			if the table of teammate worrying addenda is not empty:
				choose a random row from the table of teammate worrying addenda;
				say "[the addendum entry]";
				blank out the whole row;
	otherwise if the player is loved or the player is heroic or the player is brave:
		if the player is loved:
			if the table of teammate loved responses is not empty:
				choose a random row from the table of teammate loved responses;
				say "[the response entry]";
				blank out the whole row;
			otherwise:
				say ". [One of the group members] nods to you, but that's all you're going to get";
		otherwise if the player is heroic or the player is brave:
			if the table of teammate bravery responses is not empty:
				choose a random row from the table of teammate bravery responses;
				say "[the response entry]";
				blank out the whole row;
			otherwise:
				say ". [One of the group members] nods manfully to you out of the small measure of respect you've earned, but that's it";
	otherwise if the player is crazy:
		if the table of teammate crazy responses is not empty:
			choose a random row from the table of teammate crazy responses;
			say "[the response entry]";	
			blank out the whole row;
		otherwise:
			say ". [One of the group members] tells you to mind your own business";
	otherwise:
		if the table of teammate tolerated responses is not empty:
			choose a random row from the table of teammate tolerated responses;
			say "[the response entry]";
			blank out the whole row;
		otherwise:
			say ". You get no response at all";
		let rand be a random number between 0 and 100;
		if the player is wet and rand is less than 33:
			if the table of teammate wet addenda is not empty:
				choose a random row from the table of teammate wet addenda;
				say "[the addendum entry]";
				blank out the whole row;
		otherwise if the player is worrying and rand is less than 50:
			if the table of teammate worrying addenda is not empty:
				choose a random row from the table of teammate worrying addenda;
				say "[the addendum entry]";
				blank out the whole row;
	say ".";


Section - the queries

Table of teammate queries
query
"You approach [the noun] and try to make small talk about today's game"
"You step over to [the noun] and launch into a story about the time you kicked an amazing 50-yard field goal"
"You start to regale [the noun] with some details of your training regime"
"You wander over to [the noun] and start to ask about what kind of protein shake they drink"
"You wave to [the noun] and ask how the game is going for them"
"You approach [the noun] and try to craft an impromptu joke about the orange sports drink you all drink"
"You suggest to [the noun] that you could get a beer after the game"
"You start telling [the noun] about the time you almost won a game in overtime"
"You ask [the noun] for any tips on sleeping on planes"
"You tell [the noun] about an idea you have for making kickers more central to football"
"You step over to [the noun] and try to act like you're one of the guys"
"You find yourself offering [the noun] some tips for better play"
"You approach [the noun] with an idea you have for a line of shoes"
"You begin to offer [the noun] some of the vast wisdom you've gained as a kicker"
"You start complaining to [the noun] about how little respect kickers get in the league"
"You talk to [the noun] about how you almost got a sponsorship deal with an athlete's foot cure company"



Section - the responses

Table of teammate loved responses
response
". [One of the group members] nods amiably to you, though he makes no real attempt at conversation"
". [One of the group members] pats you on the shoulder in a friendly gesture, though he's clearly not listening to a word you're saying"
". [One of the group members] whacks you on the shoulder pads in a sign of goodwill, but doesn't actually talk to you"
". [One of the group members] nods as if he's listening to you, but he isn't"
". [One of the group members] toasts you with his little cup of sports drink and then goes back to talking to his actual friends"
". [One of the group members] gives you a vaguely condescending thumbs-up and then turns away"
". [One of the group members] nods blankly and looks away"
". [One of the group members] punches you cheerfully on the shoulder. It hurts, and by the time you've gotten over it he's already talking to someone else"
". [One of the group members] grunts in acknowledgement of your existence, which is amazing in itself, but that's about it"
". [One of the group members] suggests you keep up the good work, and then turns away"

Table of teammate bravery responses
response
". [One of the group members] nods to you and makes a brief comment on your bravery on the field, though he doesn't look like he wants to actually talk to you"
". [One of the group members] smacks you on the shoulder pads and says he saw you squaring up for a tackle on a kickoff and was impressed. Then he looks away, done with you"
". [One of the group members] shows a flicker of recognition thanks to your bravery on the field, but that's about it"
". [One of the group members] nods solemly at you"
". [One of the group members] grunts that you're tougher than he thought, and backs this up with a furious whack on your shoulder pads. By the time you've recovered he's moved away"

Table of teammate cowardly responses
response
". [One of the group members] sneers at you and suggests you man up and learn how to tackle"
". [One of the group members] tells you come back when you've worked out how to play football"
". [One of the group members] shoves you away from the group and tells you they don't want to catch your case of cowardice"
". [One of the group members] tells you cowards aren't welcome"
". [One of the group members] barks at you to go practice with the tackling dummies until you know what you're doing out on the field"

Table of teammate goat responses
response
". [One of the group members] tells you to go over to your practice equipment and learn your job"
". [One of the group members] barks at you to get away from him in case your failure is catching"
". [One of the group members] makes the sign of the cross at you as if warding off an evil spirit"
". [One of the group members] suggests that you go practice kicking instead of making idle chitchat"
". [One of the group members] waves a dismissive hand at you and tells you to go learn how to kick"
". [One of the group members] sneers and tells you to go spend some quality time with your practice equipment"
". [One of the group members] quickly turns his body to block you out of the group"
". [One of the group members] stares at you coldly and points to your practice equipment"
". [One of the group members] narrows his eyes and tells you to go practice in case the team needs you"
". [One of the group members] jerks his thumb at your practice equipment and tells you to learn how to play your position"

Table of teammate tolerated responses
response
", but [one of the group] smirks and you feel immediately unwelcome"
", but [one of the group] turns his shoulder just enough that you know you're not included in the general conversation"
", but [one of the group] chuckles at your attempt to socialise and you wander away feeling chastened"
", but [one of the group] suggests that you go hang out with someone more on your level, like the punter"
", but [one of the group] rolls his eyes and you turn away, feeling hurt"
", but [one of the group] waves you away dismissively, not wanting you to join in the conversation"
", but [one of the group] suggests you should probably go somewhere else, as you're considered bad luck"
", but [one of the group] laughs out loud when he sees you trying to be part of the group and you turn away in embarrassment"
", but [one of the group] shakes his head and stares at you with cold eyes until you back away"
", but [one of the group] curls his lip on seeing you there and you slip away"

Table of teammate crazy responses
response
", but [one of the group] makes the sign of the cross at you as if to ward you off, muttering something about your being utterly incompetent"
", but [one of the group] swirls his index finger next to his temple in the time-honour gesture of indicating that you're crazy"
", but [one of the group] waves you away disdainfully, suggesting you barely even know the rules of the game you're playing"
", but [one of the group] emphatically sends you away, suggesting you study the rulebook before you try making conversation"
", but [one of the group] motions you away and demands you spend some time reflecting on how to play the game properly"
", but [one of the group] talks loudly about how 'the kicker' doesn't know how the hell to play and you walk off"
", but [one of the group] asks rhetorically whether you even understand the game of football and you feel unwelcome"
", but [one of the group] motions at you to get away, suggesting you go read the rules"
", but [one of the group] sends you away, shaking his head over your terrible play so far"
", but [one of the group] points you toward the special teams coach and suggests you get some coaching on how to follow the rules"

Table of teammate worrying addenda
addendum
". As you walk away he makes a clearly audible joke about your apparently inability to play according to the basic rules of the game"
". As you leave he comments to a teammate that he'll be impressed if you manage to stay on the team given your behaviour"
". As you depart he mutters something about your understanding of the rules of football"
". As you're leaving he says something under his breath that sounds like it's about you"
". As you walk off you hear him joke to a teammate that you won't be on the team much longer if you keep acting the way you do"
". As you're moving away you faintly hear him talking to a nearby teammate about your behavioural issues"
". As you move off you think you hear him say something about you being too unreliable to keep on the team"
". As you walk away you think you hear him say something about hoping you're kicked off the team for your misbehaviour"

Table of teammate wet addenda
addendum
". As you're walking away he makes a crack about your wet pants and you cringe"
". As you're leaving you think you hear him say something about you having wet your pants. Guess he noticed"
". As you depart he gestures at your pants and smirks at the wet patch there. Great"
". As you're leaving you hear him laughing with a teammate about you having wet your pants"
". As you walk away you hear him excitedly discussing a new nickname for you based on you having wet your pants. Perfect"




Section - Helpers

To say One of the group members:
	say "[one of the group]" in sentence case;
	
To say one of the group:
	if the noun is singular-named:
		say "he";
	otherwise if the noun is the offensive unit:
		say "[one of the offensive unit]";
	otherwise if the noun is the offensive line:
		say "[one of the offensive linemen]";
	otherwise if the noun is the defensive unit:
		say "[one of the defensive unit]";			
	otherwise if the noun is the special teams unit:
		say "[one of the special teams unit]";
	otherwise if the noun is your teammates:
		say "one of them";
	otherwise:
		say "a random teammate";
	


To say one of the offensive unit:
	say "[one of][one of the offensive linemen][or]the quarterback[or]a running back[or]a tight end[or]a wide receiver[at random]";
	
To say One of the offensive unit:
	say "[one of the offensive unit]" in sentence case;

To say one of the offensive linemen:
	say "[one of]the left guard[or]the right guard[or]the left tackle[or]the right tackle[or]the center[at random]";
	
To say One of the offensive linemen:
	say "[one of the offensive linemen]" in sentence case;

To say one of the defensive unit:
	say "[one of]one of the defensive players[or]one of the defensive players, perhaps a linebacker,[or]a scary-looking defensive player[or]an oversized defender[or]one of the more hulking defensive players[at random]";

To say One of the defensive unit:
	say "[one of the defensive unit]" in sentence case;

To say one of the special teams unit:
	say "[one of]one of the many backups that make up the special teams unit[or]one of the many hangers on of the special teams unit[at random]";

To say One of the special teams unit:
	say "[one of the special teams unit]" in sentence case;



	
Chapter - to the punter

[ PUNTER ]
Instead of talking to the punter:
	if the table of punter sayings is not empty:
		choose a random row from the table of punter sayings;
		say "[the saying entry].";
		blank out the whole row;
	otherwise:
		say "The punter nods to you morosely and then returns to staring at his shoelaces.";
		

Table of Punter Sayings
saying
"The punter tells you a long story that ends with his dog dying in a house fire"
"The punter tells you about the time he found out his wife was cheating on him with the punter from the local high-school team"
"The punter tells you about how he played for most of a season with a fractured ankle and never got any credit for it from the other players"
"The punter tells you a lengthy story about the time he drove back from an away game to surprise his son for his birthday and was directed to the wrong restaurant as a joke"
"The punter asks if you've ever been mocked by a drive-through attendant for your performance in the previous evening's football game. He has, it turns out"
"The punter shrugs sadly and tells you about the time one of his own team's players tackled him during a punt because he hated him so much"
"The punter asks you if you think you and he will ever be respected by the rest of the team. His eyes tell you he knows the answer to that one, though"
"The punter sadly tells you about how he hasn't even bought furniture for his house because he feels like he's going to be traded or cut any day now"
"The punter motions at you to lean in and tells you in a soft, dead voice about how he's pretty sure his wife is leaving him after the game"
"The punter tells you about the time he contracted herpes from a toilet-seat and his doctor laughed at him"
"The punter sighs a bottomless sigh and tells you a story about his childhood that involves more beatings than you feel comfortable hearing of"
"The punter tells you he sometimes fantasizes about breaking his leg so he doesn't have to play anymore"
"The punter stares at the ground and tells you about the time the team bus left him behind in the parking lot"
"The punter tells you about the time the stadium announcer forgot his name and then said 'but who cares, right?' to the entire stadium"
"The punter haltingly tells you that he's thinking about just not turning up to the training facility some day to see if anyone would even notice"
"The punter tells you that sometimes he likes to imagine he's a character from Lord of the Rings as a way of getting through the day"
"The punter talks monotonously about his dental hygiene regime, ending by telling you he's probably going to have to get his gums replaced anyway"
"The punter mopily tells you about his glory days when he used to be a third-string quarterback on his high-school team"
"The punter tells you about how his son is too embarrased to tell his friends his father is a punter and instead pretends to stumble over the word, making it sound like 'plumber'"
"The punter tells you how he'll probably just go home after the game and eat icecream cake alone in his garage"


Chapter - to the head coach

Instead of talking to the head coach:
	say "[the head coach query][the head coach response]";

To say the head coach query:
	if the table of head coach queries is not empty:
		choose a random row from the table of head coach queries;
		say "[The query entry]";
		blank out the whole row;
	otherwise:
		say "You stand next to the head coach and clear your throat to speak";


To say the head coach response:
	if the player is cowardly or the player is the goat:
		if the player is cowardly:
			if the table of head coach cowardly responses is not empty:
				choose a random row from the table of head coach cowardly responses;
				say "[the response entry]";			
				blank out the whole row;
			otherwise:
				say ". His look of disgust makes it plain he has nothing to say to cowards";
		otherwise if the player is the goat:
			if the table of head coach goat responses is not empty:
				choose a random row from the table of head coach goat responses;
				say "[the response entry]";
				blank out the whole row;
			otherwise:
				say ". He looks too enraged with your failures to talk to you right now";
		let rand be a random number between 0 and 100;
		if the player is wet and rand is less than 20:
			if the table of head coach wet addenda is not empty:
				choose a random row from the table of head coach wet addenda;
				say "[the addendum entry]";
				blank out the whole row;
		otherwise if (the player is worrying or the player is crazy) and rand is less than 50:
			if the table of head coach crazy addenda is not empty:
				choose a random row from the table of head coach crazy addenda;
				say "[the addendum entry]";
				blank out the whole row;
	otherwise if the player is loved:
		if the table of head coach loved responses is not empty:
			choose a random row from the table of head coach loved responses;
			say "[the response entry]";		
			blank out the whole row;
		otherwise:
			say ". He nods to you genially, but clearly doesn't wish to talk";
	otherwise:
		if the table of head coach tolerated responses is not empty:
			choose a random row from the table of head coach tolerated responses;
			say "[the response entry]";
			blank out the whole row;
		otherwise:
			say ". He puts up a hand to cut you off";
		let rand be a random number between 0 and 100;
		if the player is wet and rand is less than 20:
			if the table of head coach wet addenda is not empty:
				choose a random row from the table of head coach wet addenda;
				say "[the addendum entry]";
				blank out the whole row;
		otherwise if (the player is worrying or the player is crazy) and rand is less than 50:
			if the table of head coach crazy addenda is not empty:
				choose a random row from the table of head coach crazy addenda;
				say "[the addendum entry]";
				blank out the whole row;
	say ".";


Section - the queries

Table of head coach queries
query
"You approach [the noun] and ask for some fatherly advice about how to focus under pressure"
"You ask [the noun] if he has any thoughts on your performance so far this season"
"You ask [the noun] about whether he thinks you're doing a good job"
"You approach [the noun] and ask him whether he trusts you to make field goals"
"You try to strike up a conversation with [the noun] about the centrality of a good kicking game to a successful team"
"You sidle up to [the noun] and espouse your theory that the kicker is secretly the central figure on any good football team"
"You suggest to [the noun] that you might deserve a little more praise than you've been getting into team meetings lately"
"You wonder aloud near [the noun] about whether you perhaps deserve a contract extension"
"You approach [the noun] and tell him you're thinking about writing a book on kicking"
"You suggest to [the noun] that he could be a little more supportive of you during practice"
"You ask [the noun] whether he could speak to the other players about being a little more friendly to you"
"You ask [the noun] whether he thinks the other players like you or not"
"You tell [the noun] you feel unappreciated by the team"
"You try to start a conversation with [the noun] about great kickers in history and where you fit in"


Section - the responses

Table of head coach loved responses
response
". He grunts something about how you should keep up the good work, then turns his attention to a diagram he's holding"
". He mutters something vaguely encouraging and returns to studying the action on the field"
". He nods distractedly at you and goes back to watching the game"
". He pats you on the shoulder absently and talks to an assistant coach about one of the linebackers"
". He makes brief eye-contact with you and then starts talking impatiently into his headset"
". He makes a sound that could be read as positive and then returns to studying an aerial photograph of the action on the field"
". He grimaces at your interruption and grunts a platitude before turning his attention back to the game"
". He winces slightly at being distracted but manages to slap you on the back supportively before returning his full attention to the game"
". He nods almost as if he's paying attention, but soon is clearly immersed in the action on the field"
". He grunts a cliche about hard work paying dividends and goes back to watching the game closely"

Table of head coach cowardly responses
response
". He suggests that you can either learn how to tackle or get the hell off his team"
". He scowls at you and shouts that you should go ask the linebackers 'how to make a damn tackle'"
". He growls something about making tackles being the heart of football and sends you on your way"
". He sneers at you and reminds you that making tackles is part of everyone's job when they're called on to do it"
". He mutters something about having cowards on the team and brushes you aside"

Table of head coach goat responses
response
". He looks at you coldly and suggests you go practice your kicking"
". He scowls and tells you to stop bothering him and go practice"
". He pointedly ignores you and mutters under his breath about the enternal uselessness of kickers"
". He points at your practice equipment and says nothing to you"
". He shakes his head disapprovingly and tells you to go and practice 'the only damn thing you were hired to do'"
". He snaps at you to leave him alone and work on your 'damn kicking'"
". He narrows his eyes and suggests you practice kicking instead of talking"
". He jerks his thumb at your practice equipment and suggests you could use less talk and more practice"
". He sneers in your face and tells you that you're living up to all his expectations by missing kicks"
". He mockingly thanks you for your latest missed kick and turns back to the game"

Table of head coach tolerated responses
response
". He grunts something incomprehensible and turns back to the game"
". He waves you away irritably"
". He walks a short distance away down the sidelines to get away from you"
". He turns away from you and studies the diagram in his hands even harder"
". He gives no indication that he even notices you standing there"
". He stares past you out at the field, ignoring you entirely"
". He grunts irritably at your interruption and returns his focus to the game"
". He mutters something about 'idiot kickers' and continues to watch the game"
". He scowls at your interruption and gestures for you to leave, immediately returning his attention to the game"
". He pushes you aside dismissively with the back of his hand and continues watching the game closely"

Table of head coach crazy addenda
addendum
". As you're leaving he advises you to get it together before he's forced to replace you"
". As you walk away he shouts to you to shape up or be prepared to ship out"
". As you leave he takes enough time to yell at you to learn how to play the game properly or he'll get rid of you"
". As you walk away you hear his talking to himself about how you're on very thin ice"
". As you leave he tells a nearby assistant coach he doesn't expect you to be on the team much longer"
". As you're leaving you hear him ask the special teams coach whether he has any other candidates to take over from you"
". As you're leaving you hear him talking into his headset about wishing he had a replacement for you"
". As you walk off you hear him ask a random player whether he wants to take over kicking. They laugh"

Table of head coach wet addenda
addendum
". As you walk away you think you hear him say something about the effect of your wet pants on team morale"
". As you're leaving you overhear him announcing the fact you wet your pants over his headset"
". As you leave you think you hear him telling a nearby coach that he saw you wet your pants and that this seemed predictable to him"
". As you walk off you hear him shout at you to at least try not to piss yourself again on game day"
". As you leave you could swear you hear him muttering about your soiled pants being a perfect metaphor for your play"



Section - to the special teams coach

[ SPECIAL TEAMS COACH ]
Instead of talking to the special teams coach:
	if the table of special teams coach sayings is not empty:
		choose a random row from the table of special teams coach sayings;
		say "[the saying entry], [one of]then looks mystically into the middle distance[or]then folds his hands in his lap[or]then closes his eyes and smiles beatifically[at random].";
		blank out the whole row;
	otherwise:
		say "The special teams coach nods to you sagely, but appears to have no new wisdom to dispense.";


Table of Special Teams Coach Sayings
saying
"[The noun] says, 'What is the sound of one foot kicking?'"
"[The noun] says, 'Like a paper cup you are full of your own opinions and speculations. How can you learn to kick unless you first empty your cup?'"
"[The noun] says 'An old tree grows on a cold rock in winter. Nowhere is there any warmth'"
"[The noun] says 'I came from brillancy. And return to brillancy. What is this?'"
"[The noun] says 'Great Waves is your name. So stay on this field tonight. Imagine that you are an ocean. You are no longer a kicker who is afraid. You are those huge waves sweeping everything before them, swallowing all in their path. Do this and you will be the greatest kicker in the land'"
"[The noun] says 'For fifty-six years I lived as best I could. Making my way in this world. Now the rain has ended, the clouds are clearing. The blue sky has a full moon'"
"[The noun] says 'A man traveling across a field encountered a tiger. He fled, the tiger after him. Coming to a precipice, he caught hold of the root of a wild vine and swung himself down over the edge. The tiger sniffed at him from above. Trembling, the man looked down to where, far below, another tiger was waiting to eat him. Only the vine sustained him. Two mice, one white and one black, little by little started to gnaw away the vine. The man saw a luscious strawberry near him. Grasping the vine with one hand, he plucked the strawberry with the other. How sweet it tasted!'"
"[The noun] says 'Upon retiring, sleep as if you had entered your last sleep. Upon awakening, leave your bed behind you instantly as if you had cast away a pair of old shoes'"
"[The noun] says 'Have the fearless attitude of a hero and the loving heart of a child'"
"[The noun] says 'Retire at a regular hour. Partake of food at regular intervals. Eat with moderation and never to the point of satisfaction'"
"[The noun] says 'Receive a guest with the same attitude you have when alone. When alone, maintain the same attitude you have in receiving guests'"
"[The noun] says 'Watch what you say, and whatever you say, practice it'"
"[The noun] says 'When an opportunity comes do not let it pass by, yet always think twice before acting'"
"[The noun] says 'Do not regret the past. Look to the future'"
"[The noun] says 'I consider the positions of kings and rulers as that of dust motes. I observe treasures of gold and gems as so many bricks and pebbles. I look upon the finest silken robes as tattered rags. I see myriad worlds of the universe as small seeds of fruit, and the greatest lake in India as a drop of oil on my foot. I perceive the teachings of the world to be the illusion of magicians. I discern the highest conception of emancipation as a golden brocade in a dream, and view the holy path of the illuminated ones as flowers appearing in one's eyes. I see meditation as a pillar of a mountain, Nirvana as a nightmare of daytime. I look upon the judgment of right and wrong as the serpentine dance of a dragon, and the rise and fall of beliefs as but traces left by the four seasons'"
"[The noun] says 'You may not know exactly who is suffering, but question yourself: What is the essence of this mind? Think only of this. You will need no more. Covet nothing. Your end which is endless is as a snowflake dissolving in the pure air'"
"[The noun] says 'My miracle is that when I feel hungry I eat, and when I feel thirsty I drink'"
"[The noun] says 'Your head must feel very heavy, if you are carrying around a stone like that in your mind'"
"[The noun] says 'We are soldiers of humanity, aiming to save all sentient beings'"
"[The noun] says 'Everything has to die and has just so long to live'"


Section - the holder

Instead of talking to the holder when the player is on the field and KickType is not "field goal" and KickType is not "extra point":
	say "The backup quarterback isn't here. He only comes out to hold for you on field goals and extra points.";
	
Instead of talking to the holder when KickStatus is "pending signal":
	say "'[one of]Just kick it![or]Kick the damn ball![or]Give the signal and kick the stupid ball[or]Would you just get on with it?[or]Hurry up![or]Don't talk to me, do your job![or]Just do your job, would you?[or]You're making us look bad![or]Just get on with it[or]Hurry up and do your job![at random]' he [one of]hisses[or]mutters[or]shouts[or]grunts[at random].";	

Instead of talking to the holder when KickStatus is "pending kick":
	say "'[one of]What the hell are you doing, you moron?![or]You idiot![or]What the…![or]Oh you total bastard![at random]' he [one of]shrieks[or]shouts[or]yells[or]screams[at random].";

Instead of talking to the holder:
	if the table of holder sayings is not empty:
		choose a random row from the table of holder sayings;
		say "[the saying entry].";
		blank out the whole row;
	otherwise:
		say "You've probably tried talking to the holder enough at this point. He does kind of hate you, you know.";


Table of Holder Sayings
saying
"[The noun] turns his back on you immediately, eliminating any chance of conversation"
"[The noun] curtly tells you to save it for someone who cares and walks off"
"[The noun] looks surprised you'd even try talking to him, then regathers himself and turns away from you coldly"
"[The noun] puts up a hand to indicate you shouldn't even bother saying anything to him"
"[The noun] ignores you utterly, affecting to look through your head at the game going on behind you"
"[The noun] smiles superciliously and moves away"
"[The noun] sneers at your attempt at conversation and walks away"
"[The noun] takes a sudden interest in his fingernails, studying them assiduously until you leave"
"[The noun] gives you a cold stare and says nothing"
"[The noun] has no interest in talking to you"


Chapter - special cases based on kicking

Section - get on the field

Instead of talking to a singular-named game figure when KickStatus is "get on the field":
	if the noun is not the head coach:
		choose a random row from the table of get on the field sayings;
		say "[the saying entry].";

Instead of talking to a plural-named game figure when KickStatus is "get on the field":
	if the noun is not the head coach:
		say "[One of the group] suggests you run onto the field.";

Table of Get On the Field Sayings
saying
"[The noun] cut[s] you off and note[s] that you should probably be getting onto the field"



Section - pending signal

Instead of talking to a singular-named game figure when KickStatus is "pending signal":
	choose a random row from the table of pending signal sayings;
	say "[the saying entry].";
	
Instead of talking to a plural-named game figure when KickStatus is "pending signal":
	say "[One of the group] suggests you get on with it.";

Table of Pending Signal Sayings
saying
"[The noun] suggest[s] that you shut up and get on with the game"


Section - pending kick

Instead of talking to a singular-named game figure when KickStatus is "pending kick":
	if KickType is "kickoff" or KickType is "onside kick":
		choose a random row from the table of pending kickoff sayings;
		say "[the saying entry].";
	otherwise:
		choose a random row from the table of pending kick sayings;
		say "[the saying entry].";
	
Instead of talking to a plural-named game figure when KickStatus is "pending kick":
	if KickType is "kickoff" or KickType is "onside kick":
		say "[One of the group] suggests you kick the ball.";
	otherwise:
		say "Nobody is listening to you because you're meant to be kicking the ball already.";

Table of Pending Kickoff Sayings
saying
"[The noun] suggest[s] that you shut up and kick off"


Table of Pending Kick Sayings
saying
"[The noun] is busy actually playing the game"



Section - post kick

Instead of talking to a singular-named game figure when KickStatus is "post kick":
	choose a random row from the table of get off the field sayings;
	say "[the saying entry].";
	
Instead of talking to a plural-named game figure when KickStatus is "post kick":
	say "[One of the group] suggests you leave the field.";

Table of Get Off the Field Sayings
saying
"[The noun] point[s] out that you should probably be running off the field"







Chapter - inanimate objects

[ INANIMATE OBJECTS ]
Instead of talking to something singular-named:
	Say "[one of][The noun] doesn't seem to be in a talking mood[or][The noun] has nothing to say to you[or]You tell [the noun] about your feelings. It's a good listener[or]It's hard to tell whether [the noun] is interested or not[or][The noun] lets you tell it all about your personal insecurities[or][The noun] gives no outward sign of caring[or][The noun] seems strangely indifferent to you[or][The noun] acts a bit standoffish[or]You aren't completely sure if [the noun] is even listening to you[or]You talk at length about the difficulties of being a kicker with [the noun], but it's not very satisfying[or]Talking to an inanimate object is always such a good sign[at random].";
	
Instead of talking to something plural-named:
	Say "[one of][The noun] don't seem to be in a talking mood[or][The noun] have nothing to say to you[or]You tell [the noun] about your feelings. They're good listeners[or]It's hard to tell whether [the noun] are interested or not[or][The noun] let you tell them all about your personal insecurities[or][The noun] give no outward sign of caring[or][The noun] seem strangely indifferent to you[or][The noun] act a bit standoffish[or]You aren't completely sure if [the noun] are even listening to you[or]You talk at length about the difficulties of being a kicker with [the noun], but it's not very satisfying[or]Talking to inanimate objects is always such a good sign[at random].";


Chapter - Helpers
	
To say s:
	say "[if the noun is singular-named]s[end if]"

To say One of the group:
	if the noun is the offensive unit:
		say "[one of]The quarterback[or]The running back[at random]";
	otherwise if the noun is the defensive unit:
		say "A random defensive player";
	otherwise if the noun is the special teams unit:
		say "A backup linebacker";
	otherwise if the noun is the assistant coaches:
		say "One of the coaches, who you think may be in charge of cornerbacks,";
	otherwise if the noun is the officials:
		say "The referee";
	otherwise if the noun is your teammates:
		say "A random player";









Part - The Kicker Simulator






Chapter - Player Kicking Actions


[KickMode is text that varies. KickMode is "player";]
KickMode is text that varies. KickMode is "player";
KickType is text that varies. KickType is "kickoff";
KickStatus is text that varies. KickStatus is "get on the field";
[KickStatus is text that varies. KickStatus is "get on the field";]


PreKickDelay is a number that varies. PreKickDelay is 4.
PostKickDelay is a number that varies. PostKickDelay is 4.
KickingMistakesMade is a number that varies. KickingMistakesMade is 0.




Section - Kicker status

A person can be loved or tolerated or the goat. A person is usually tolerated.
A person can be sane or worrying or crazy. A person is usually sane.


Section - Wearing a helmet

Your helmet is a thing worn by the player.
The description is "[if the player is wearing the helmet]Looks fine from in here[otherwise]It's your trusty helmet. Hasn't seen much action, to be honest, and is embarrassingly free of scratches[end if].".
The odour is "[if the player is wearing the helmet]It smells like your head in here[otherwise]Your helmet smells helmety[end if].".
The taste is "[if the player is wearing the helmet]Your tongue can't reach a single surface of the helmet while you're wearing it[otherwise]It tastes like a football helmet, as it turns out[end if].".
The feel is "You rap your knuckles on the top of your helmet and feel reassured by its solidity.".

Instead of dropping the helmet:
	Say "You consider throwing down your helmet in a fit of pique, but decide against it.";

Instead of taking off the helmet when the player is in the field:
	Say "The rulebook is pretty clear about not removing your helmet on the field of play.";





Section - Calling a timeout

Calling a timeout is an action applying to nothing.
Understand "call timeout" and "timeout" and "request timeout" and "give timeout signal" and "get timeout" and "take timeout" and "take a timeout" and "signal a timeout" and "signal timeout" and "request a timeout" and "call a timeout" as calling a timeout.

Check calling a timeout:
	if the player is not in the field:
		Say "[one of]You form your hands proudly into the celebrated 'T' sign to call a timeout, but since you're on the sidelines nobody really cares[or]You make a 'T' with your hands, but what with you being on the sidelines it has no real effect[or]You give the timeout signal to no one in particular. Nothing happens[or]You signal for a timeout. You do not get one because you're not actually in the game right now, but at least you tried[or]You give the timeout signal and watch as nothing happens[or]You signal for a timeout. Nothing happens[or]Because you're on the sidelines rather than in the game, your signal for a timeout goes unrewarded[at random]." instead;		
	if (PostPlayPossession is 1 and OffenceTimeouts is 0) or (PostPlayPossession is -1 and DefenceTimeouts is 0):
		Say "[one of]You start to form your hands into the 'T' of the timeout signal, then remember your team has no timeouts left to spend[or]You think about calling a timeout, but somehow recall that your team has none left[or]You start calling a timeout, then remember your team doesn't have any left, so you stop[or]As your team has no timeouts remaining, you refrain from calling one and getting penalised[or]You think about asking for a timeout, but your team has none left. Oh well[at random]." instead;
	if KickStatus is "pending kick" and (KickType is "field goal" or KickType is "extra point"):
		say "[one of]You raise your hands to give the timeout signal, but of course you can't call timeout while the play is in progress. Oops[or]You make the 'T' sign of a timeout, but as the play is already in progress, it achieves squarely nothing[or]You signal for a timeout while the play goes on around you. Unsurprisingly, it doesn't help[at random]." instead;
	if KickStatus is "kickoff tackle":
		say "[one of]In an odd moment of delusion, you try to call a timeout[or]Rather than address the situation at hand, you attempt to call a timeout[at random]. It doesn't help." instead;
		

Carry out calling a timeout:
	if the player is loved then now the player is tolerated;
	if the player is worrying then now the player is crazy;
	if the player is sane then now the player is worrying;
	if PostPlayPossession is 1 then decrease OffenceTimeouts by 1;
	if PostPlayPossession is -1 then decrease DefenceTimeouts by 1;
	increase KickingMistakesMade by 1;
	if KickStatus is "pending kick" then change KickStatus to "pending signal";
	if KickStatus is "pending signal" then change PreKickDelay to 4;
	
Report calling a timeout:
	if KickStatus is "pending signal":
		Say "[one of]You call a timeout[or]You make the universal 'T' sign for a timeout[or]You give the signal to call a timeout[or]You request a timeout[or]You signal for a timeout[at random] and the referee [one of]blows his whistle to stop play[or]stops play[or]acknowledges you by blowing play dead[or]blows play dead with his whistle[or]acknowledges you with his whistle[at random]. [one of]You stand around feeling a little unsure as to why that was necessary[or]You ask yourself why you just did that[or]You quietly wonder why you called the timeout[or]You ponder what your reasoning for calling a timeout was[at random] and [one of]avoid the coach's baleful stare from the sidelines[or]try not to notice the coach looking furious on the sidelines[or]pretend you can't hear the hoarse screaming of the coach from the sidelines[or]try to ignore your teammates suspicious looks[at random]. [one of]Eventually the play is reset for you to attempt the kick again[or]The teams eventually reset after the confusion and get ready to play again[at random].";
	otherwise:
		Say "[one of]You call a timeout[or]You make the universal 'T' sign for a timeout[or]You give the signal to call a timeout[or]You request a timeout[or]You signal for a timeout[at random] and the referee [one of]blows his whistle to stop play[or]stops play[or]acknowledges you by blowing play dead[or]blows play dead with his whistle[or]acknowledges you with his whistle[at random]. [one of]You stand around feeling a little unsure as to why that was necessary[or]You ask yourself why you just did that[or]You quietly wonder why you called the timeout[or]You ponder what your reasoning for calling a timeout was[at random] and [one of]avoid the coach's baleful stare from the sidelines[or]try not to notice the coach looking furious on the sidelines[or]pretend you can't hear the hoarse screaming of the coach from the sidelines[or]try to ignore your teammates suspicious looks[at random]." 


Section - Running onto the field

Understand "on" and "field" and "onto field" and "on field" and "onto the field" and "on the field" as east when the player is on the sidelines.

Check going east when the player is on the sidelines:
	if Player is in The field:
		Say "You're on the field already." instead;
	if KickStatus is "off the field":
		Say "Wait until you're actually needed out there." instead;
	if the player is not wearing the helmet:
		Say "You're not allowed on the field if you're not wearing your helmet." instead;
		
Carry out going east when the player is on the sidelines:
	if the player is on the bench:
		say "(First getting off the bench.)";
		now the player is standing;
	say "[if the player has the cup]You toss away your paper cup and[otherwise]You[end if] [one of]run onto the field[or]rush onto the field[or]head onto the field[or]jog onto the field[or]hurry onto the field[at random].";
	if the player has the cup:
		Now the cup is empty;
		Move the cup to the drinks table;	
	change KickStatus to "pending signal";			
	change PreKickDelay to 4;
	

Section - Signaling

Signaling is an action applying to nothing.
Understand "signal" and "give signal" and "give the signal" and "raise arm" as signaling.

Check signaling:
	if KickStatus is "pending kick":
		if KickType is "field goal" or KickType is "extra point":
			Say "[one of]Instead of actually kicking the ball you signal you're ready to kick again[or]You signal your readiness to kick even though the ball has been snapped and there's no time for anything except kicking[or]You signal you're ready to kick. Shame the ball's already been snapped and your readiness is kind of meaningless now[or]You signal the kick. Everyone's quite busy, though, except the holder, who stares at you incredulously[at random]." instead;
		otherwise:
			Say "[one of]You signal you're ready to kick. Again[or]Confusing everyone involved, you signal that you're still ready to kick[or]Helping no one, you give the ready signal again[or]You signal that you're ready to kick again for good measure, no one looks amused[or]You give the kick signal again, despite already having done so and everyone being at the ready[or]Rather than kick, you decide to give the signal again, throwing everyone into some confusion[at random]." instead;
	otherwise If player is not in The field:
		Say "[one of]You raise your arm proudly to signal you're ready to kick, but since you're standing on the sidelines everyone pretty much ignores you[or]You signal your readiness to kick while standing on the sidelines. Someone snickers[or]You raise you arm high in the air and then wonder why you did so given that you're standing on the sidelines[or]You give the kicking signal, then watch as absolutely nothing happens because you're on the sidelines[or]You raise you arms to give the kicking signal, then quickly retract it as you remember you're on the sidelines[at random]." instead;			
	otherwise If KickStatus is not "pending signal":
		Say "[one of]You wave your arm around, but nothing much happens[or]You signal you're going to kick and a few people look at you incredulously[or]You give the signal you're going to kick, but given this isn't the right time for that, nothing much happens[or]You give the kicking signal, but now isn't exactly the time, so nothing happens[at random]." instead;
		
Carry out signaling:
	if KickStatus is "pending signal":
		if KickType is "kickoff" or KickType is "onside kick":
			Change KickStatus to "pending kick";
			Change PreKickDelay to 4;
			Change PostKickDelay to 4;
		otherwise if KickType is "extra point" or KickType is "field goal":
			Change KickStatus to "pending kick";
			Change PreKickDelay to 2;
			Change PostKickDelay to 4;
		rule succeeds;

Report signaling:
	if KickType is "kickoff":
		Say "You [one of][one of]raise[or]lift[at random] your arm to signal[or]give the signal that[or]signal to that[or][one of]raise[or]lift[at random] your arm, signaling that[at random] you're [one of]about[or]going[or]ready[at random] to kickoff and the [one of]coverage[or]kickoff[at random] [one of]team[or]unit[at random] [one of]gets ready[or]gets set[or]prepares[or]gets into position[or]stands at the ready[at random] to [one of]charge down[or]hurtle down[or]sprint down[or]rush down[at random] the field [one of]toward the return man[or]in an inevitable collision with the return team[or]to collide with the return team[or]to stop the return man[or]to tackle the return man[or]to annihilate the return man[at random].";
	otherwise if KickType is "onside kick":
		Say "You [one of][one of]raise[or]lift[at random] your arm to signal[or]give the signal that[or]signal that[or][one of]raise[or]lift[at random] your arm, signaling that[at random] you're [one of]about[or]going[or]ready[at random] to perform the onside kick and the [one of]recovery[or]onside kick[at random] [one of]team[or]unit[at random] [one of]gets ready[or]gets set[or]prepares[or]gets into position[or]stands at the ready[at random] [one of]to recover the ball[or]to chase down the ball[or]to make the recovery[or]to go after the ball[or]to get the ball before the return team can[at random].";		
	otherwise if KickType is "field goal" or KickType is "extra point":
		Say "You [one of][one of]raise[or]lift[at random] your arm to signal[or]give the signal that[or]signal to that[or][one of]raise[or]lift[at random] your arm, signaling that[at random] you're [one of]about[or]going[or]ready[at random] to kick to the holder. He [one of]turns and signals[or]turns and nods[or]turns and stretches a hand out[at random] to the center who [one of]snaps the ball[or]fires the ball back[or]sends the ball flying back[at random]. The holder [one of]expertly catches it[or]controls the slightly wide snap[or]deftly catches it[or]seamlessly catches the snap[or]controls the snap[at random] and [one of]turns the laces to face away from you[or]turns aside the laces[or]positions it at the read[or]positions the ball on its end[or]positions the ball[or]readies the ball[at random]. The two lines of players [one of]crash into each other with a furious sound as the opposition struggle to get to you[or]crunch together as the opposition fight to get past and block the kick[or]collide with huge force as they fight to control the space[or]slam into each other, the opposition team striving for the block[or]collide viciously as the the opposition attempts to get to you[at random]. [one of]All that remains is to[or]Now everything depends on you:[or]Now's it's up to you to[or]All that's left is to[or]Now you just need to[or]Now it's your job to[at random] [bold type]kick the ball[roman type].";



Section - Kicking

Kicking is an action applying to one thing.
Understand "kick [something]" as kicking.

Practising is an action applying to nothing.
Understand "practice" and "practice kick" and "practice kicking" and "practise" and "practise kick" and "practise kicking" as practising.

Check practising:
	if the player is on the field:
		Say "[one of]Now is not the time for practice[or]It's not practice time. It's game time[or]Practice time is over[or]Not right now[at random]." instead;
	if the player is on the bench:
		if the player is seated:
			Say "It's very difficult to practice kicking while sitting on the bench like that." instead;
		otherwise if the player is standing:
			Say "It's very difficult to practice kicking while standing on the bench like that." instead;
	if the player is not wearing the helmet:
		if the player has the cup:
			say "(First setting aside your helmet and paper cup.)";
		otherwise:
			say "(First setting aside your helmet.)";
	otherwise if the player has the cup:
		say "(First setting aside your paper cup.)";
Report practising:
	say "[practice kick report]";
	


Does the player mean kicking the practice ball when the player is on the sidelines:
	it is very likely;
	
Check kicking the practice ball:
	if the player is on the field:
		Say "[one of]Now is not the time for practice[or]It's not practice time. It's game time[or]Practice time is over[or]Not right now[at random]." instead;
	if the player is on the bench:
		if the player is seated:
			Say "It's very difficult to practice kicking while sitting on the bench like that." instead;
		otherwise if the player is standing:
			Say "It's very difficult to practice kicking while standing on the bench like that." instead;
	if the player is not wearing the helmet:
		if the player has the cup:
			say "(First setting aside your helmet and paper cup.)";
		otherwise:
			say "(First setting aside your helmet.)";
	otherwise if the player has the cup:
		say "(First setting aside your paper cup.)";

Report kicking the practice ball:
	say "[practice kick report]";
	
To say practice kick report:
	Say "[one of]You kick the practice ball firmly into the net[or]You swing your leg and launch the practice ball into the net[or]You thwack the practice ball into the back of the net[or]You step up and boot the practice ball into the net[or]You step into the kick and hammer the practice ball into the back of the net[or]You kick the practice ball forcefully into the net[or]You hit the practice ball nicely into the back of the net[at random]. [one of]That felt pretty good[or]That would have been good from 50 yards away at least[or]It's good! Well, it would have been[or]You raise your arms in a little celebration of the imagined field goal made[or]You look around to see if anyone saw how nicely you did that. Nope[or]That was a nice piece of kicking right there[or]Nicely done[or]Your confidence rises a little[at random]. [one of]You set the ball back up in the holder for next time[or]You replace the ball into the holder for another kick[or]You set the ball up again[or]You reset the ball in the holder[or]You fish the ball out of the net and set it up in the holder again[or]You grab the ball and put it into the holder again[at random].";
	say "[if the player is not wearing the helmet and the player has the cup][line break](You retrieve your helmet and paper cup.)[otherwise if the player is not wearing the helmet][line break](You retrieve your helmet.)[otherwise if the player has the cup][line break](You retrieve your paper cup.)[end if]"




Does the player mean kicking the kicking ball when the player is on the field:
	it is very likely;

Check kicking the kicking ball:
	if the noun is not the kicking ball:
		say "Yes, you're a 'kicker', but please, try to restrain yourself." instead;
	otherwise if KickStatus is "pending signal":
		say "You have to signal before you can kick so that everyone knows what's happening." instead;
	otherwise if KickStatus is not "pending kick":
		Say "Now is not really a good time for that." instead;

Carry out kicking the kicking ball:
	if KickStatus is "pending kick":
		Change KickStatus to "kicked";
		Change PreKickDelay to 4;

Report kicking the kicking ball:
	if KickType is "kickoff":
		Say "[one of]You sprint forward and swing your foot into the ball, launching it into high and far into the air toward the opposition endzone[or]You run forward and kick the ball with great force, booming the kick through the air toward the opposition endzone[or]You rush forward and step into the kick, launching the ball deep into the opposition's territory[or]You take a few quick steps to the ball and launch a kick deep toward the opposition endzone[or]You stride forward and hit a nice kick that sails deep into the opposition's half[or]You rush up and boom the kick deep into the opposition's territory[or]You run forward and swing your foot at the ball, sending it far and deep into the opposition half[at random]. The [one of]coverage[or]kickoff[at random] [one of]team[or]unit[at random] [one of]streams forward with the ball[or]sprints ahead up the field with the ball[or]vanishes upfield beneath your kick[or]flies up the field behind the kick[or]zooms up the field under the ball[or]surges forward with your kick[at random].";
	otherwise if KickType is "onside kick":
		Say "You [one of]jog[or]quickly step[or]run[or]stride[at random] forward and [one of]swing your foot into the ball[or]kick dowards on the ball[or]kick the ball[or]strike the ball nicely[or]perform the onside kick[or]strike the ball well[or]hit the ball well[or]kick down on the ball[at random], [one of]bouncing it into the air[or]sending it bounding into the air[or]bouncing it high in the air[or]sending it careening through the air[or]sending it skipping[or]sending it bouncing[at random] toward the opposition [one of]kick return[or]return[at random] [one of]team[or]unit[at random]. The [one of]onside kick recovery[or]kick recover[or]recovery[or]onside recovery[at random] [one of]team[or]unit[at random] [one of]stream toward the ball[or]sprint toward the bouncing ball[or]sprint after the ball[or]race after the kick[or]surge after the kick[or]burst forward after the ball[or]head after the ball as one[at random].";		
	otherwise if KickType is "field goal" or KickType is "extra point":
		Say "You [one of]step forward[or]race forward[or]rush forward[or]spring forward[or]step through[or]rush up[or]stride forward[or]stride through[at random] and [one of]swing your foot into the ball[or]kick the ball[at random], [one of]launching it[or]sending it flying[or]sending it[or]lifting it[or]hitting it[at random] toward the goal posts.";
		

Check kicking:
	if the noun is not the kicking ball and the noun is not the practice ball:
		say "Yes, you're a 'kicker', but please try to restrain yourself." instead;




Section - Tackling


A person can be cowardly or neutral or brave or heroic. A person is usually neutral.


TackleMade is text which varies. TackleMade is "n/a".

Tackling is an action applying to nothing.
Understand "tackle" and "make tackle" and "tackle man" and "tackle player" and "tackle return man" and "make the tackle" and "make a tackle" as tackling.

Check tackling:
	if KickStatus is not "kickoff tackle":
		say "[one of]You look around you for a likely subject to tackle, but then think better of it[or]Tackling someone right now would be rather impolite[or]You imagine tackling someone instead, it's safer and better[or]You think about tackling someone, and then you don't do it[or]As amusing as tackling someone right now might be, it's just not a good idea[or]Instead of tackling someone, you don't tackle anyone at all[at random]." instead;
		
Carry out tackling:
	let rand be a random number between 0 and 100;
	if rand is less than 20:
		now the player is heroic;
		if the player is tolerated then now the player is loved;
		if the player is the goat then now the player is tolerated;		
		Change TackleMade to "true";
	otherwise:
		now the player is brave;
		if the player is tolerated then now the player is loved;
		if the player is the goat then now the player is tolerated;		
		Change TackleMade to "false";
		

Every turn:
	if TackleMade is "n/a" and KickStatus is "kickoff tackle":
		now the player is cowardly;
		now the player is the goat;
		Change TackleMade to "no attempt";
		

Every turn:
	if TackleMade is "true":
		let rand be a random number between 0 and 100;
		if rand is less than 30:
			say "There is a blur of motion and the beginning of a sickening crun--";
			End the game saying "You are unconscious. You scored [score] points in [turn count] turns.";		
		otherwise:
			Change PlayCalled to "player kickoff";
			Change KickStatus to "made tackle";
			Change FinalPlayResult to "kickoff tackle made";	
			Change TackleMade to "n/a";	
	otherwise if TackleMade is "false":
		Change PlayCalled to "player kickoff";		
		Change KickStatus to "missed tackle";
		Change FinalPlayResult to "kickoff return touchdown";	
		Change TackleMade to "n/a";	
	otherwise if TackleMade is "no attempt":
		Change PlayCalled to "player kickoff";
		Change KickStatus to "no tackle";
		Change FinalPlayResult to "kickoff return touchdown";
		Change TackleMade to "n/a";	
			
		


Section - Running off the field

Understand "off" and "off field" and "off the field" and "leave" and "sidelines" and "to sidelines" and "sideline" and "to sideline" as west when the player is on the field.

Check going west when the player is on the field:
	if Player is not in the field:
		Say "[one of]You're not on the field[or]That will be really difficult given that you're not on the field[or]You try running off the field, but since you're not on it, it doesn't work out very well[or]You think about running off the field, but realise you can't do that from the sidelines[or]You're not actually on the field, so running off it would be a little challenging[or]You try to run off the field, but nothing happens because you're not on the field in the first place[at random]." instead;
		
Carry out going west when the player is on the field:
	Say "You [one of]run[or]jog[or]walk[or]rush[or]hurry[or]head[or]get[or]wander[or]stroll[at random] [one of]off the field[or]back to the sidelines[or]to the sidelines[or]back to the bench area[or]off[at random].[paragraph break]";
	if KickStatus is "pending signal" or KickStatus is "pending kick":
		if OffenceTimeouts is not 0:
			Say "Your [one of]head coach[or]coach[at random] [one of]calls a timeout[or]is forced to call a time out[or]has to burn a timeout[or]uses a timeout[or]quickly calls a timeout[at random] to avoid a penalty after you [one of]randomly run off the field[or]surprise everyone by running off the field[or]oddly run off the field[or]shockingly run off the field[at random] instead of kicking. [one of]He looks extraordinarily unimpressed[or]It would be an understatement to say he looks displeased[or]He doesn't look very happy with you[or]He's not happy[at random].";
			Decrease OffenceTimeouts by 1;
		otherwise:
			Say "[one of]The referee blows his whistle[or]The referee's whistle sounds[or]You hear the referee blow his whistle[at random] and [one of]your team is penalised for a delay of game[or]he penalises you for a delay of game[or]he announces a delay of game penalty[at random], [one of]as you ran off the field in the middle of the play and your team has no timeouts remaining[at random]. [one of]The coach stares at you in disbelief[or]The coach looks deeply, deeply unhappy with you[or]The coach looks around at the assistant coaches and shrugs helplessly at your incompetence[at random].";
			Decrease PrePlayFieldPosition by 10;
			Change Clock to "stopped";
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is not 3:
			Change KickStatus to "get on the field";
			if the player is loved then now the player is tolerated;
			if the player is worrying then now the player is crazy;
			if the player is sane then now the player is worrying;			
		otherwise:			
			Change KickStatus to "ejected";
	otherwise:
		Change KickStatus to "off the field";



Chapter - Player Kicking Simulator


Every turn:
	if KickStatus is "get on the field":
		Decrease PreKickDelay by 1;		
		if PreKickDelay is 3:
			Say "Your coach [one of]tells you to[or]calls you over and tells you to[or]waves at you to[or]nods to you and instructs you[or]orders you to[at random] [bold type]run onto the field[roman type].";
		otherwise if PreKickDelay is 2:
			Say "Your coach [one of]shouts at you to[or]screams at you to[or]yells hoarsely that you need to[or]boils with anger as he demands that you[or]seeths as he tells you to[or]grinds his teeth and tells you to[at random] [bold type]run onto the field[roman type].";
		otherwise if PreKickDelay is 1:
			Say "Your coach [one of]yells that you better[or]screams with a red face that you have to[or]roars in your face to[at random] [bold type]run on[roman type] [one of]immediately[or]right now[or]without delay[at random].";
		if PreKickDelay is 0:
			Change PreKickDelay to 4;
			Change KickStatus to "delayed getting on the field";
		otherwise:
			rule succeeds;
	otherwise if KickStatus is "pending signal":
		Decrease PreKickDelay by 1;		
		if PreKickDelay is 3:
			if KickType is "kickoff" or KickType is "onside kick":			
				Say "The [one of]rest of the[or]members of the[at random] [one of]kickoff team[or]kickoff unit[or]coverage team[or]coverage unit[at random] wait for you to [bold type]give the signal[roman type].";
			otherwise:
				Say "The holder [one of]quietly[or]silently[or]calmly[at random] [one of]waits for your[or]crouches waiting for your[or]watches for your[at random] [bold type]signal[roman type].";
		otherwise if PreKickDelay is 2:
			if KickType is "kickoff" or KickType is "onside kick":		
				Say "Your teammates [one of]wait impatiently[or]watch closely[or]nervously wait[or]shift a little as they wait[at random] for your [bold type]signal[roman type].";
			otherwise:
				Say "The holder [one of]looks a little nervous[or]looks at you with concern[or]looks perplexed[at random] as he [one of]continues waiting[or]finds himself still waiting[or]keeps watching[at random] for your [bold type]signal[roman type].";
		otherwise if PreKickDelay is 1:
			Say "The play clock has almost [one of]run out[or]expired[or]run down[at random]. You really [one of]need to[or]have to[or]must[at random] [bold type]give the signal[roman type] [one of]immediately[or]right now[or]without delay[at random].";
		if PreKickDelay is 0:
			Change PreKickDelay to 4;
			Change KickStatus to "delayed signal";
		otherwise:	
			rule succeeds;		
	otherwise if KickStatus is "pending kick" and (KickType is "kickoff" or KickType is "onside kick"):
		Decrease PreKickDelay by 1;
		if PreKickDelay is 3:
			Say "Your teammates on [one of]the kickoff unit[or]kickoff team[or]the kick team[at random] [one of]wait[or]are waiting[or]stand ready[or]watch[at random] for you to [bold type]kick the ball[roman type].";
		otherwise if PreKickDelay is 2:
			Say "Your teammates [one of]start to look little confused[or]begin looking concerned[or]look perplexed[at random] about why you [one of]wont[or]don't[at random] just [bold type]kick the ball[roman type].";
		otherwise if PreKickDelay is 1:
			Say "The play clock has almost [one of]run out[or]expired[or]run down[at random]. You really [one of]need to[or]have to[or]must[at random] [bold type]kick the ball[roman type] [one of]immediately[or]right now[or]without delay[at random].";
		if PreKickDelay is 0:
			Change PreKickDelay to 4;
			Change KickStatus to "delayed kick";
		otherwise:	
			rule succeeds;
	otherwise if KickStatus is "pending kick" and (KickType is "field goal" or KickType is "extra point"):
		Decrease PreKickDelay by 1;
		if PreKickDelay is 0:
			Change PreKickDelay to 4;
			Change KickStatus to "delayed kick";
		otherwise:	
			rule succeeds;
	otherwise if KickStatus is "post kick":
		Decrease PostKickDelay by 1;
		if PostKickDelay is 3:
			Say "[one of]Now would be a good time to[or]This is the point where you[or]Now's the time for you to[or]That's your cue to[or]Time for you to[or]You're done for now, so you ought to[at random] [bold type]run off the field[roman type].";
		otherwise if PostKickDelay is 2:
			Say "The coach [one of]shouts at you to[or]screams from the sidelines for you to[or]yells at you to[at random] [bold type]run off the field[roman type].";
		otherwise if PostKickDelay is 1:
			Say "The coach [one of]rages and gestures at you to[or]rages on the sidelines and shouts at you to[or]eyes the playclock running down to zero and screams at you to[or]boils with anger on the sidelines and signals for you to[at random] [bold type]run off the field[roman type] [one of]immediately[or]right now[or]without delay[at random].";
		if PostKickDelay is 0:
			Change PostKickDelay to 4;
			Change KickStatus to "delayed getting off the field";
		otherwise:
			rule succeeds;
			
	
Every turn:
	if KickStatus is "delayed getting on the field":
		if OffenceTimeouts is not 0:
			Say "Your [one of]head coach[or]coach[at random] [one of]calls a timeout[or]is forced to call a time out[or]has to burn a timeout[or]uses a timeout[or]quickly calls a timeout[at random] because you [one of]didn't get onto the field in time for your kick[or]delayed too long before getting out onto the field[or]didn't bother running onto the field to kick[or]didn't even run onto the field to kick[or]just stood on the sidelines instead of kicking[at random]. He looks [one of]extraordinarily unimpressed[or]very, very upset[or]extremely displeased[at random]. [no line break]";
			Decrease OffenceTimeouts by 1;
		otherwise:
			Say "[one of]The referee blows his whistle[or]The referee's whistle sounds[or]You hear the referee blow his whistle[at random] and [one of]your team is penalised for a delay of game[or]he penalises you for a delay of game[or]he announces a delay of game penalty[at random], [one of]as you simply stood on the sidelines and didn't come out to kick[at random]. [one of]The coach stares at you in disbelief[or]The coach looks deeply, deeply unhappy with you[or]The coach looks around at the assistant coaches and shrugs helplessly at your incompetence[at random]. [no line break]";
			Decrease PrePlayFieldPosition by 10;
			Change Clock to "stopped";
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is less than 3:
			Say "He [one of]suggests that perhaps this time you would do everyone a favour and[or]grabs you by the jersey and orders you to[or]smacks you hard in the chest with his palms and tells you to[or]whacks you on the shoulder and yells at you to[at random] [bold type]run onto the field[roman type].";
			if the player is loved then now the player is tolerated;
			if the player is worrying then now the player is crazy;
			if the player is sane then now the player is worrying;				
			Change KickStatus to "get on the field";
			rule succeeds;
		otherwise:			
			say "Rather than try to continue the game with you any longer, he just fires you.";
			Change KickStatus to "ejected";
	otherwise if KickStatus is "delayed signal" or (KickStatus is "delayed kick" and (KickType is "kickoff" or KickType is "onside kick")):
		if OffenceTimeouts is not 0:
			Say "[one of]Having watched you stand motionless until the play clock is about to run out[or]You manage to just stand around on the field instead of doing your job[or]Observing that you are apparently just not going to do your job[or]Having waited for too long for you to kick[at random], [one of]the coach is forced to call a timeout to avoid a penalty[or]the coach calls a timeout to avoid a penalty[or]the coach angrily calls a timeout[at random]. [one of]His face looks grim[or]He looks… less than pleased[or]He doesn't look excited about your performance[or]His face is silently furious[at random]. [no line break]";
			Decrease OffenceTimeouts by 1;
		otherwise:
			Say "[one of]The referee blows his whistle[or]The referee's whistle sounds[or]You hear the referee blow his whistle[at random] and [one of]your team is penalised for a delay of game[or]he penalises you for a delay of game[or]he announces a delay of game penalty[at random], [one of]as you simply stood there and didn't kick[at random]. [one of]The coach stares at you in disbelief[or]The coach looks deeply, deeply unhappy with you[or]The coach looks around at the assistant coaches and shrugs helplessly at your incompetence[at random]. [no line break]";
			Decrease PrePlayFieldPosition by 10;
			Change Clock to "stopped";
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is less than 3:
			Say "He [one of]yells over[or]shouts[or]screams[or]yells[at random] from the sidelines about [one of]your basic job requirements[or]your perhaps short-lived role on the team[or]the need for you to do your job[at random]. He [one of]suggests that you[or]orders you to[or]barks at you to[or]demands that you[at random] [bold type]give the signal[roman type] and get on with it.";	
			if the player is loved then now the player is tolerated;
			if the player is worrying then now the player is crazy;
			if the player is sane then now the player is worrying;	
			Change KickStatus to "pending signal";
			rule succeeds;
		otherwise:			
			say "Rather than try to continue the game with you any longer, he just fires you.";
			Change KickStatus to "ejected";
	otherwise if KickStatus is "delayed kick" and (KickType is "field goal" or KickType is "extra point"):
		Say "[one of]The holder's eyes catch yours with a expression of loathing just before he is crushed beneath a deluge of opposition players[or]The holder just manages to cradle the ball to his chest before he's dived on by the opposition team[or]The holder stares at you in disbelief as he's tackle and piled up on by the opposition players[or]The holder maintains hostile eye contact with you the entire time that he is crushed beneath the opposition players[or]The holder huddles in foetal position over the ball as the opposition team piles on top of him[at random]. Your coach [one of]is exploding with anger as he waves at you to[or]furiously gestures at you to[or]roars with incoherent rages and gestures for you to[at random] [bold type]run off the field[roman type]. [no line break]";
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is less than 3:
			Say "He [one of]yells over[or]shouts[or]screams[or]yells[at random] from the sidelines about [one of]your basic job requirements[or]your perhaps short-lived role on the team[or]the need for you to do your job[at random].";	
			if the player is loved then now the player is tolerated;
			if the player is worrying then now the player is crazy;
			if the player is sane then now the player is worrying;				
			Change KickStatus to "miskicked";
		otherwise:	
			say "Rather than try to continue the game with you any longer, he just fires you.";
			Change KickStatus to "ejected";
	otherwise if KickStatus is "delayed getting off the field":
		if DefenceTimeouts is not 0:
			Say "[one of]Having watched you stand around on the field until the play clock is about to run out[or]You manage to just stand around on the field instead of running off[or]Observing that you are apparently just not going to leave the field in time for the next play to be run[or]Having waited for too long for you to leave the field[at random], [one of]the coach is forced to call a timeout to avoid a penalty[or]the coach calls a timeout to avoid a penalty[or]the coach angrily calls a timeout[at random]. [one of]His face looks grim[or]He looks… less than pleased[or]He doesn't look excited about your performance[or]His face is silently furious[at random]. [no line break]";
			Decrease DefenceTimeouts by 1;
		otherwise:
			Say "[one of]The referee blows his whistle[or]The referee's whistle sounds[or]You hear the referee blow his whistle[at random] and [one of]your team is penalised for a delay of game[or]he penalises you for a delay of game[or]he announces a delay of game penalty[at random], [one of]as you simply stood there rather than getting off the field[at random]. [one of]The coach stares at you in disbelief[or]The coach looks deeply, deeply unhappy with you[or]The coach looks around at the assistant coaches and shrugs helplessly at your incompetence[at random]. [no line break]";
			Increase PrePlayFieldPosition by 10;
			Change Clock to "stopped";
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is less than 3:
			Say "[one of]Two of your teammates drag you from the field between them on the coach's instructions[or]Your teammates grab you and pull you off the field and over to the coach[or]You teammates angrily drag you from the field and over to the coach[or]Two teammates roughly wrestle you over to the sidelines on the coach's orders[at random]. [one of]He spends several precious seconds of his time yelling in your face about the need to get off the field in a timely fashion[or]He stares into your eyes and you know your time with the team is limited[or]He speaks eerily calmly about the need to leave the field after your play is over, it's scary[or]He puts his hands on your shoulders and shakes his head slowly, then turns away[at random].";	
			if the player is loved then now the player is tolerated;
			if the player is worrying then now the player is crazy;
			if the player is sane then now the player is worrying;				
			Change KickStatus to "off the field";
			Move the player to The sidelines;
			rule succeeds;
		otherwise:			
			say "Rather than try to continue the game with you any longer, he just fires you.";
			Change KickStatus to "ejected";
	otherwise if KickStatus is "no tackle":
		Increase KickingMistakesMade by 1;
		if KickingMistakesMade is greater than 2:
			Change KickStatus to "ejected";

		
[ EJECTION]	

Every turn:
	If KickStatus is "ejected":
		End the game saying "You screwed up one too many times and got fired. You scored [score] points in [turn count] turns.";





Part - The Game Simulator


Chapter - The Variables

[ REPORTING ]

PlayReport is a text that varies. The PlayReport is "nothing".
ScoreboardReport is a text that varies. The ScoreboardReport is "nothing".
CrowdReport is a text that varies. The CrowdReport is "nothing".
RefereeReport is a text that varies. RefereeReport is "nothing".
TimeoutReport is a text that varies. TimeoutReport is "nothing".
CoachReport is a text that varies. CoachReport is "nothing".


[ PLAYER INPUT ]



[ TIME TRACKING ]

Q is a number that varies. Q is 1. [ Q = Quarter ]
T is a number that varies. T is 900. [ T = Game Time ]
TheMinutes is a number that varies. TheMinutes is 15.
TheSeconds is a number that varies. TheSeconds is 0.
PT is a number that varies. PT is 0. [ PT = Play Time ]

Clock is text that varies. Clock is "pregame".

TwoMinuteWarningGiven is text that varies. TwoMinuteWarningGiven is "false".



[ DOWN, DISTANCE, POSITION, and POSSESSION TRACKING ]

PrePlayDown is a number that varies. PrePlayDown is 1.
PostPlayDown is a number that varies. PostPlayDown is 1.
PrePlayDistance is a number that varies. PrePlayDistance is 0.
PostPlayDistance is a number that varies. PrePlayDistance is 0.
PrePlayFieldPosition is a number that varies. PrePlayFieldPosition is 30. [ Always 0-100 for each side, swaps ]
PreReturnFieldPosition is a number that varies.
PostPlayFieldPosition is a number that varies. PostPlayFieldPosition is 30.
PrePlayPossession is a number that varies. PrePlayPossession is 1.
PostPlayPossession is a number that varies. PostPlayPossession is 1.

PostPlayFieldPositionText is a text that varies.

[ SCORE TRACKING ]

HomeScore is a number that varies. HomeScore is 0.
AwayScore is a number that varies. AwayScore is 0.

OffenceMode is text that varies. OffenceMode is "normal".
DefenceMode is text that varies. DefenceMode is "normal".
OffenceTrailingBy is a number that varies. OffenceTrailingBy is 0. [Tracks how far behind the team with the ball is. ]
RunChance is a number that varies. RunChance is 47.



[ TIME OUTS ]

OffenceTimeouts is a number that varies. OffenceTimeouts is 3.
DefenceTimeouts is a number that varies. DefenceTimeouts is 3.



[ PLAY TRACKING ]

PlayCalled is text that varies. PlayCalled is "player kickoff".
PreviousPlayCalled is text that varies. PreviousPlayCalled is "none".
PlayResult is text that varies. PlayResult is "none".
PlayDistance is text that varies. PlayDistance is "none".
FinalPlayResult is text that varies. FinalPlayResult is "none".
PreviousPlayResult is a text that varies. PreviousPlayResult is "none".

GrossYardsGained is a number that varies.
NetYardsGained is a number that varies.

GrossYardsReturned is a number that varies.
NetYardsReturned is a number that varies.

PreviousPlayNetYards is a number that varies.



[ KICKING ]

PuntDistance is a number that varies.
KickDistance is a number that varies.
FieldGoalDistance is a number that varies.



[ PLAY REPORTING ]

FieldPart is text that varies. FieldPart is "".
SubjectPossessive is text that varies. SubjectPossessive is "".
Subject is text that varies. Subject is "".
CoreResult is text that varies. CoreResult is "".
ExtendedResult is text that varies. ExtendedResult is "".


[ STATS ]

HP is a number that varies. HP is 0. [Home plays]
HPA is a number that varies. HPA is 0. [Home pass attempts]
HPC is a number that varies. HPC is 0. [Home pass completions]
HPY is a number that varies. HPY is 0. [Home pass yards]
HPTD is a number that varies. HPTD is 0. [Home pass TDs]
HPI is a number that varies. HPI is 0. [Home pass interceptions]
HRA is a number that varies. HRA is 0. [Home rush attempts]
HRY is a number that varies. HRY is 0. [Home rush yards]
HRTD is a number that varies. HRTD is 0. [Home rush TDs]
HFGA is a number that varies. HFGA is 0. [Home FG attempts]
HFGM is a number that varies. HFGM is 0. [Home FGs made]
HXPA is a number that varies. HXPA is 0. [Home XP attempts]
HXPM is a number that varies. HXPM is 0. [Home XPs made]
HPT is a number that varies. HPT is 0. [Home punts]

AP is a number that varies. HP is 0.
APA is a number that varies. APA is 0. [Away pass attempts]
APC is a number that varies. APC is 0. [Away pass completions]
APY is a number that varies. APY is 0. [Away pass yards]
APTD is a number that varies. APTD is 0. [Away pass TDs]
API is a number that varies. API is 0. [Away pass interceptions]
ARA is a number that varies. ARA is 0. [Away rush attempts]
ARY is a number that varies. ARY is 0. [Away rush yards]
ARTD is a number that varies. ARTD is 0. [Away rush TDs]
AFGA is a number that varies. AFGA is 0. [Away FG attempts]
AFGM is a number that varies. AFGM is 0. [Away FGs made]
AXPA is a number that varies. AXPA is 0. [Away XP attempts]
AXPM is a number that varies. AXPM is 0. [Away XP made]
APT is a number that varies. APT is 0. [Away punts]





Chapter - Football Simulator, Initial Results of the Play



When play begins:
	if SimulationMode is "player":
		Change KickStatus to "get on the field";
		Change KickType to "kickoff";
		Change PlayCalled to "player kickoff";
		Change KickMode to "player";
	otherwise if SimulationMode is "auto":
		Change KickStatus to "none";
		Change KickType to "none";
		Change PlayCalled to "kickoff";
		Change KickMode to "auto";


[ CALCULATE INITIAL OUTCOME OF RUN AND PASS PLAYS ]

Every turn:
	Let Rand be a random number between 0 and 100;
	if PlayCalled is "pass":
		if Rand is less than 10:
			change PlayResult to "sack";
		otherwise if Rand is less than 40:
			change PlayResult to "incompletion";
		otherwise if Rand is less than 42:
			change PlayResult to "loss";
		otherwise if Rand is less than 46:
			change PlayResult to "no gain";
		otherwise if Rand is less than 75:
			change PlayResult to "gain";
			change PlayDistance to "short";
		otherwise if Rand is less than 96:
			change PlayResult to "gain";
			change PlayDistance to "medium";
		otherwise if Rand is less than 101:
			change PlayResult to "gain";
			change PlayDistance to "big";
	otherwise if PlayCalled is "run":
		if Rand is less than 10: 
			change PlayResult to "loss";
		otherwise if Rand is less than 30:
			change PlayResult to "no gain";
		otherwise if Rand is less than 80:
			change PlayResult to "gain";
			change PlayDistance to "short";
		otherwise if Rand is less than 96:
			change PlayResult to "gain";
			change PlayDistance to "medium";
		otherwise if Rand is less than 101:
			change PlayResult to "gain";
			change PlayDistance to "big";
	otherwise:
		Change PlayResult to PlayCalled;
	If DebugMode is "DEBUG" then say "PLAY RESULT: [PlayResult] [line break]";



[ GROSS YARDAGE ]

Every turn:
	Change GrossYardsGained to 0;
	Let Rand be a random number between 0 and 100;
	If PlayCalled is "run": 
		if PlayResult is "loss":
			Change GrossYardsGained to a random number between -1 and -4;	
		otherwise if PlayResult is "no gain":
			Change GrossYardsGained to 0;		
		otherwise if PlayResult is "gain" and PlayDistance is "short":
			Change GrossYardsGained to a random number between 1 and 4;
		otherwise if PlayResult is "gain" and PlayDistance is "medium":
			Change GrossYardsGained to a random number between 5 and 15;
		otherwise if PlayResult is "gain" and PlayDistance is "big":
			if Rand is less than 80:
				Change GrossYardsGained to a random number between 20 and 40;
			otherwise:
				Change GrossYardsGained to a random number between 40 and 80;
	otherwise if PlayCalled is "pass":
		if PlayResult is "incompletion" or PlayResult is "no gain":
			Change GrossYardsGained to 0;
		otherwise if PlayResult is "sack":
			Change GrossYardsGained to a random number between -1 and -8;
		otherwise if PlayResult is "loss":
			Change GrossYardsGained to a random number between -1 and -4;		
		otherwise if PlayResult is "gain" and PlayDistance is "short":
			Change GrossYardsGained to a random number between 1 and 8;
		otherwise if PlayResult is "gain" and PlayDistance is "medium":
			Change GrossYardsGained to a random number between 6 and 20;
		otherwise if PlayResult is "gain" and PlayDistance is "big":
			if Rand is less than 90:
				Change GrossYardsGained to a random number between 20 and 40;
			otherwise:
				Change GrossYardsGained to a random number between 40 and 80;
	otherwise if PlayCalled is "take a knee":
		Change GrossYardsGained to -2;
	otherwise: 
		change GrossYardsGained to 0;
	If DebugMode is "DEBUG" then say "GROSS YARDS: [GrossYardsGained] [line break]";
		
	


[ CHANGE OF DOWN, DISTANCE AND POSITION (WITHOUT CHECKING VALIDITY) ]

Every turn:
	if PlayCalled is "run" or PlayCalled is "pass" or PlayCalled is "take a knee" or PlayCalled is "spike the ball":
		Change PostPlayDown to PrePlayDown + 1;
		Change PostPlayDistance to PrePlayDistance - GrossYardsGained;
		Change PostPlayFieldPosition to PrePlayFieldPosition + GrossYardsGained;
	




Chapter - Football Simualtor, Adjusted Results of the Play


[ CORRECT RESULTS OF PLAY ]

Every turn:
	Change NetYardsGained to GrossYardsGained; [First appoximation]
	Change FinalPlayResult to PlayResult; [First approximation]
	If PostPlayDistance is greater than 0 and PostPlayDown is 5:
		Change FinalPlayResult to "turnover on downs";
	if GrossYardsGained is greater than 0:
		if PostPlayFieldPosition is greater than 99:	
			Change FinalPlayResult to "touchdown";
			Change NetYardsGained to (100 - PrePlayFieldPosition);
			if NetYardsGained is less than 5:
				change PlayDistance to "short";
			otherwise if NetYardsGained is less than 20:
				change PlayDistance to "medium";
			otherwise:
				change PlayDistance to "big";
		otherwise If PostPlayDistance is less than 1:
			Change FinalPlayResult to "first down";
			Change PostPlayDown to 1;
			if (100 - PostPlayFieldPosition) is less than 10:
				[ GOAL TO GO ]
				Change PostPlayDistance to (100 - PostPlayFieldPosition);
			otherwise:
				Change PostPlayDistance to 10;
	otherwise if GrossYardsGained is less than 0:
		if PostPlayFieldPosition is less than 1:
			Change FinalPlayResult to "safety";
	If DebugMode is "DEBUG" then say "FINAL PLAY RESULT: [FinalPlayResult]!";	




Chapter - Football Simualtor, Special events


[ ADD INTERCEPTION AND FUMBLE POSSIBILITY ]

Every turn:
	if PlayCalled is "pass" and (FinalPlayResult is "loss" or FinalPlayResult is "no gain" or FinalPlayResult is "gain" or FinalPlayResult is "touchdown"):
		Let Rand be a random number between 0 and 100;
		if Rand is less than 4:
			[WE HAVE AN INTERCEPTION]
			if FinalPlayResult is "touchdown":
				[ INTERCEPTION IN THE END ZONE ]
				Change FinalPlayResult to "interception touchback";
			otherwise:
				[ INTERCEPTION The field OF PLAY ]
				Change FinalPlayResult to "interception";
				Change PreReturnFieldPosition to PostPlayFieldPosition;
				[ CALCULATE THE RETURN YARDAGE ]
				Let Rand2 be a random number between 0 and 100;
				if Rand2 is less than 50:
					Change GrossYardsReturned to a random number between 0 and 5;
				otherwise if Rand2 is less than 90:
					Change GrossYardsReturned to a random number between 5 and 20;
				otherwise if Rand2 is less than 101:
					Change  GrossYardsReturned to a random number between 15 and 100;
				[ CALCULATE NEW POST PLAY POSITION WITH RETURN ]
				Change PostPlayFieldPosition to PreReturnFieldPosition - GrossYardsReturned;
				Change NetYardsReturned to GrossYardsReturned;
				if PostPlayFieldPosition is less than 1:
					[ RETURNED ALL THE WAY FOR A TOUCHDOWN ]
					Change NetYardsReturned to PreReturnFieldPosition;
					Change FinalPlayResult to "interception return touchdown";
	otherwise if (PlayCalled is "run" or PlayCalled is "pass") and (FinalPlayResult is "gain" or FinalPlayResult is "no gain"):
		Let Rand be a random number between 0 and 100;
		if Rand is less than 4:
			[ WE HAVE A FUMBLE ]
			Change FinalPlayResult to "fumble";
			Change PreReturnFieldPosition to PostPlayFieldPosition;
			[ CALCULATE THE RETURN YARDAGE ]
			Let Rand2 be a random number between 1 and 100;
			if Rand2 is less than 80:
				Change GrossYardsReturned to a random number between 0 and 5;
			otherwise if Rand2 is less than 95:
				Change GrossYardsReturned to a random number between 5 and 20;
			otherwise if Rand2 is less than 101:
				Change  GrossYardsReturned to a random number between 15 and 100;
			[ CALCULATE NEW POST PLAY POSITION WITH RETURN ]
			Change PostPlayFieldPosition to PreReturnFieldPosition - GrossYardsReturned;
			Change NetYardsReturned to GrossYardsReturned;
			if PostPlayFieldPosition is less than 1:
				[ RETURNED ALL THE WAY FOR A TOUCHDOWN ]
				Change NetYardsReturned to PreReturnFieldPosition;
				Change FinalPlayResult to "fumble return touchdown";


[ CPU FIELD GOAL RESULT ]

Every turn:
	if PlayCalled is "field goal":
		Change KickDistance to (100 - PrePlayFieldPosition + 10 + 7);
		Let Rand be a random number between 0 and 100;
		if KickDistance is less than 20 and Rand is less than 95:
			Change FinalPlayResult to "successful field goal";
		otherwise if KickDistance is less than 30 and Rand is less than 90:
			Change FinalPlayResult to "successful field goal";
		otherwise if KickDistance is less than 40 and Rand is less than 70:
			Change FinalPlayResult to "successful field goal";
		otherwise if KickDistance is less than 50 and Rand is less than 60:
			Change FinalPlayResult to "successful field goal";
		otherwise if KickDistance is less than 55 and Rand is less than 50:
			Change FinalPlayResult to "successful field goal";
		otherwise if KickDistance is less than 60 and Rand is less than 20:
			Change FinalPlayResult to "successful field goal";
		otherwise:
			Change FinalPlayResult to "unsuccessful field goal";	
	


[ EXTRA POINT RESULT ]

Every turn:
	if PlayCalled is "extra point":
		Let Rand be a random number between 0 and 100;
		if Rand is less than 98:
			Change FinalPlayResult to "successful extra point";
		otherwise:
			Change FinalPlayResult to "unsuccessful extra point";
		
	
[ GO FOR TWO RESULT ]

Every turn:
	if PlayCalled is "two point conversion":
		Let Rand be a random number between 0 and 100;
		if Rand is less than 45:
			Change FinalPlayResult to "successful two point conversion";
		otherwise:
			Change FinalPlayResult to "unsuccessful two point conversion";
	


[ PUNT RESULT ]

Every turn:
	If PlayCalled is "punt":
		Change KickDistance to a random number between 30 and 70;
		Change PostPlayFieldPosition to PrePlayFieldPosition + KickDistance;
		Change GrossYardsReturned to 0;
		Change NetYardsReturned to 0;
		If DebugMode is "DEBUG" then say "Gross punt yards: [KickDistance] [line break]";
		if PostPlayFieldPosition is greater than 99:
			Change FinalPlayResult to "punt touchback";
			Change PostPlayFieldPosition to 80;
	


[ CPU KICKOFF RESULT ]

Every turn:
	if PlayCalled is "kickoff":
		Let Rand be a random number between 0 and 100;
		if Rand is less than 20:
			Change FinalPlayResult to "kickoff touchback";
			Change KickDistance to (100 - PrePlayFieldPosition);
			Change PostPlayFieldPosition to 80;
		otherwise:
			Change FinalPlayResult to "kickoff return";
			Change KickDistance to a random number between 55 and 68;
			Change GrossYardsReturned to a random number between 12 and 25;
			Change NetYardsReturned to GrossYardsReturned;
			Change PostPlayFieldPosition to PrePlayFieldPosition +  KickDistance - NetYardsReturned;
	
	

[ SAFETY KICKOFF RESULT ]

Every turn:
	If PlayCalled is "safety kickoff":
		Change FinalPlayResult to "safety kickoff return";
		Change KickDistance to a random number between 45 and 60;
		Change GrossYardsReturned to a random number between 12 and 25;
		Change NetYardsReturned to GrossYardsReturned;
		Change PostPlayFieldPosition to PrePlayFieldPosition + KickDistance - NetYardsReturned;
	


[ ONSIDE KICK RESULT ]

Every turn:
	If PlayCalled is "onside kick":
		Let Rand be a random number between 0 and 100;
		if Rand is less than 10:
			Change FinalPlayResult to "successful onside kick";
			Change KickDistance to a random number between 10 and 12;
		otherwise:
			Change FinalPlayResult to "unsuccessful onside kick";	
			Change KickDistance to a random number between 10 and 15;	
		Change GrossYardsReturned to 0;
		Change NetYardsReturned to GrossYardsReturned;
		Change PostPlayFieldPosition to PrePlayFieldPosition + KickDistance - NetYardsReturned;

Every turn:
	if DebugMode is "DEBUG" then say "FINAL RESULT POST SPECIAL EVENTS: [FinalPlayResult]";	








Chapter - Player action results

Every turn:
	If PlayCalled is "player kickoff":
		if KickStatus is "kicked":
			Change KickDistance to a Random number between 60 and 80;
			if KickDistance is greater than 70:
				Change FinalPlayResult to "kickoff touchback";
				Change PostPlayFieldPosition to 80;
			otherwise:
				let rand be a random number between 0 and 100;
				if rand is less than 5:
					change KickStatus to "kickoff tackle";
					Change FinalPlayResult to "kickoff tackle";
				otherwise:
					Change FinalPlayResult to "kickoff return";
					Change GrossYardsReturned to a random number between 12 and 25;
					Change NetYardsReturned to GrossYardsReturned;
					Change PostPlayFieldPosition to PrePlayFieldPosition +  KickDistance - NetYardsReturned;
		otherwise if KickStatus is "missed tackle":
			change FinalPlayResult to "kickoff return touchdown";
		otherwise if KickStatus is "made tackle":
			change FinalPlayResult to "kickoff return tackle";
			change NetYardsReturned to KickDistance + 5;
			Change PostPlayFieldPosition to PrePlayFieldPosition + KickDistance - NetYardsReturned;
		otherwise if KickStatus is "no tackle":
			change FinalPlayResult to "kickoff return touchdown";
	If PlayCalled is "player onside kick":
		if KickStatus is "kicked":
			Let Rand be a random number between 0 and 100;
			if Rand is less than 10:
				Change FinalPlayResult to "successful onside kick";
				Change KickDistance to a random number between 10 and 12;
			otherwise:
				Change FinalPlayResult to "unsuccessful onside kick";	
				Change KickDistance to a random number between 10 and 15;	
			Change GrossYardsReturned to 0;
			Change NetYardsReturned to GrossYardsReturned;
			Change PostPlayFieldPosition to PrePlayFieldPosition + KickDistance - NetYardsReturned;
		otherwise:
			Change FinalPlayResult to PreviousPlayResult;			
	otherwise if PlayCalled is "player field goal":
		if KickStatus is "kicked":
			Change KickDistance to (100 - PrePlayFieldPosition + 10 + 7);
			Let Rand be a random number between 0 and 100;
			if KickDistance is less than 20 and Rand is less than 95:
				Change FinalPlayResult to "successful field goal";
			otherwise if KickDistance is less than 30 and Rand is less than 90:
				Change FinalPlayResult to "successful field goal";
			otherwise if KickDistance is less than 40 and Rand is less than 70:
				Change FinalPlayResult to "successful field goal";
			otherwise if KickDistance is less than 50 and Rand is less than 60:
				Change FinalPlayResult to "successful field goal";
			otherwise if KickDistance is less than 55 and Rand is less than 50:
				Change FinalPlayResult to "successful field goal";
			otherwise if KickDistance is less than 60 and Rand is less than 20:
				Change FinalPlayResult to "successful field goal";
			otherwise:
				Change FinalPlayResult to "unsuccessful field goal";		
			if FinalPlayResult is "successful field goal":
				if the player is tolerated then now the player is loved;
				if the player is the goat then now the player is tolerated;
			otherwise if FinalPlayResult is "unsuccessful field goal":
				if the player is tolerated then now the player is the goat;
				if the player is loved then now the player is tolerated;
		otherwise if KickStatus is "miskicked":
			Change FinalPlayResult to "miskicked field goal";
		otherwise:
			Change FinalPlayResult to PreviousPlayResult;
	otherwise if PlayCalled is "player extra point":
		if KickStatus is "kicked":
			let rand be a random number between 0 and 100;
			if rand is less than 95:
				Change FinalPlayResult to "successful extra point";	
			otherwise:
				Change FinalPlayResult to "unsuccessful extra point";
				if the player is tolerated then now the player is the goat;
				if the player is loved then now the player is tolerated;
		otherwise if KickStatus is "miskicked":
			Change FinalPlayResult to "miskicked extra point";
		otherwise:
			Change FinalPlayResult to PreviousPlayResult;			









Chapter - Football Simulation, Post Play Adjustments


Section - Field position

[ ADJUST POST PLAY FIELD POSITION ]

Every turn:
	If FinalPlayResult is "touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "kickoff return touchdown":
		Change PostPlayFieldPosition to 98;
	otherwise if FinalPlayResult is "successful field goal" or FinalPlayResult is "successful extra point" or FinalPlayResult is "unsuccessful extra point" or FinalPlayResult is "successful two point conversion" or FinalPlayResult is "unsuccessful two point conversion":
		Change PostPlayFieldPosition to 30;
	otherwise if FinalPlayResult is "kickoff touchback" or FinalPlayResult is "punt touchback" or FinalPlayResult is "interception touchback":
		Change PostPlayFieldPosition to 80;
	otherwise if FinalPlayResult is "safety":
		Change PostPlayFieldPosition to 20;
	otherwise if FinalPlayResult is "unsuccessul field goal":
		Change PostPlayFieldPosition to PrePlayFieldPosition - 7;
	



Section - Down and distance

[ ADJUST SPECIAL CASE POST PLAY DOWN AND DISTANCE ]

Every turn:
	if FinalPlayResult is "successful field goal" or FinalPlayResult is "successful extra point" or FinalPlayResult is "successful extra point" or FinalPlayResult is "successful two point conversion" or FinalPlayResult is "unsuccessful two point conversion" or FinalPlayResult is "safety":
		[ KICK OFF SITUATION ]
		Change PostPlayDown to 1;
		Change PostPlayDistance to 0;
	otherwise if FinalPlayResult is "turnover on downs" or FinalPlayResult is "punt" or FinalPlayResult is "interception" or FinalPlayResult is "fumble" or FinalPlayResult is "kickoff touchback" or FinalPlayResult is "punt touchback" or FinalPlayResult is "interception touchback" or FinalPlayResult is "unsuccessful field goal" or FinalPlayResult is "miskicked field goal" or FinalPlayResult is "kickoff return" or FinalPlayResult is "kickoff return tackle" or FinalPlayResult is "punt return" or FinalPlayResult is "unsuccessful onside kick" or FinalPlayResult is "successful onside kick":
		[ TURNOVER SITUATION ]
		Change PostPlayDown to 1;
		Change PostPlayDistance to 10;
	otherwise if FinalPlayResult is "touchdown" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "kickoff return touchdown":
		[ TOUCHDOWN SITUATION ]
		Change PostPlayDown to 1;
		Change PostPlayDistance to 2;
	otherwise if (100 - PostPlayFieldPosition) is less than PostPlayDistance:
		[ GOAL TO GO SITUATION ]
		Change PostPlayDistance to (100 - PostPlayFieldPosition);		
		



Section - Penalties

[ PENALTIES ]

Every turn:
	if T > 120:
		Let Rand be a random number between 0 and 1000;
		[ FALSE START ]
		If (PlayCalled is "run" or PlayCalled is "pass") and PrePlayFieldPosition is greater than 15 and Rand is less than 30:
			Change FinalPlayResult to "false start";
			Change PostPlayDown to PrePlayDown;
			Change PostPlayDistance to PrePlayDistance + 5;
			Change PostPlayFieldPosition to PrePlayFieldPosition - 5;
		[ HOLDING ]
		otherwise If (PlayCalled is "pass" or PlayCalled is "run") and PrePlayFieldPosition is greater than 20 and Rand is less than 20:
			Change FinalPlayResult to "holding";
			Change PostPlayDown to PrePlayDown;
			Change PostPlayDistance to PrePlayDistance + 10;
		[ OFFSIDE ]
		otherwise if (PlayCalled is "run" or PlayCalled is "pass") and PrePlayFieldPosition is greater than 20 and PrePlayFieldPosition is less than 90 and Rand is less than 10:
			if NetYardsGained is less than 5 and FinalPlayResult is not "touchdown":
				Change FinalPlayResult to "offside";
				Change PostPlayFieldPosition to PrePlayFieldPosition + 5;
				Change PostPlayDistance to PrePlayDistance - 5;
				if PostPlayDistance is less than 1:
					Change PostPlayDown to 1;
					Change PostPlayDistance to 10;
		[ PASS INTERFERENCE ]
		otherwise If PlayResult is "incompletion" and PrePlayFieldPosition is less than 81 and Rand is less than 6:
			Change FinalPlayResult to "pass interference";
			Change PostPlayDistance to 10;
			Change GrossYardsGained to a random number between 10 and (99 - PrePlayFieldPosition);	
			Change NetYardsGained to GrossYardsGained;
			Change PostPlayFieldPosition to PrePlayFieldPosition + NetYardsGained;
			Change PostPlayDown to 1;
			if PrePlayFieldPosition + NetYardsGained is greater than 90:
				Change PostPlayDistance to (100 - (PrePlayFieldPosition + NetYardsGained));
			otherwise:
				Change PostPlayDistance to 10;





Section - Score changes

[ CHANGE SCORE ]

Every turn:
	If FinalPlayResult is "touchdown" and PreviousPlayResult is not "touchdown":
		if PrePlayPossession is 1:
			Increase HomeScore by 6;
		otherwise:
			Increase AwayScore by 6;
	otherwise If FinalPlayResult is "successful extra point" and PreviousPlayResult is not "successful extra point":
		if PrePlayPossession is 1:
			Increase HomeScore by 1;
			Increase score by 1;
		otherwise:
			Increase AwayScore by 1;
	otherwise if FinalPlayResult is "successful two point conversion" and PreviousPlayResult is not "successful two point conversion":
		if PrePlayPossession is 1:
			Increase HomeScore by  2;
			Increase score by 2;
		otherwise:
			Increase AwayScore by 2;
	otherwise if (FinalPlayResult is "interception return touchdown"  and PreviousPlayResult is not "interception return touchdown") or (FinalPlayResult is "fumble return touchdown" and PreviousPlayResult is not "fumble return touchdown") or (FinalPlayResult is "kickoff return touchdown" and PreviousPlayResult is not "kickoff return touchdown"):
		if PrePlayPossession is 1:
			Increase AwayScore by 6;
		otherwise:
			Increase HomeScore by 6;
	otherwise if FinalPlayResult is "safety" and PreviousPlayResult is not "safety":
		if PrePlayPossession is 1:
			Increase AwayScore by 2;
		otherwise:
			Increase HomeScore by 2;
	otherwise if FinalPlayResult is "successful field goal" and PreviousPlayResult is not "successful field goal":
		if PrePlayPossession is 1:
			Increase HomeScore by 3;
			Increase score by 3;
		otherwise:
			Increase AwayScore by 3;
	if PrePlayPossession is 1:
		Change OffenceTrailingBy to AwayScore - HomeScore;
	otherwise:
		Change OffenceTrailingBy to HomeScore - AwayScore;
	



Section - Change of possession

[ CHANGE OF POSSESSION ]

Every turn:
	if FinalPlayResult is "turnover on downs" or FinalPlayResult is "punt" or FinalPlayResult is "kickoff touchback" or FinalPlayResult is "punt touchback" or FinalPlayResult is "interception" or FinalPlayResult is "interception touchback" or FinalPlayResult is "fumble" or FinalPlayResult is "unsuccessful field goal" or FinalPlayResult is "miskicked field goal" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "kickoff return touchdown" or FinalPlayResult is "kickoff return" or FinalPlayResult is "kickoff return tackle" or FinalPlayResult is "safety kickoff return" or FinalPlayResult is "unsuccessful onside kick":
		if PrePlayPossession is 1:
			Change PostPlayPossession to -1;
		otherwise:
			Change PostPlayPossession to 1;
		Change PostPlayFieldPosition to (100 - PostPlayFieldPosition);
	




Chapter - Football Simulation, reporting the play

[ VERBOSE REPORT PLAY ]



Every turn:
	if VerboseMode is "VERBOSE":
		Change TheMinutes to T divided by 60;
		Change TheSeconds to the remainder after dividing T by 60;
		say "[if Q is less than 5]Q[Q][otherwise]OT[end if], [TheMinutes]:[if TheSeconds is less than 10]0[TheSeconds][otherwise][TheSeconds][end if] : [PrePlayDown] & [PrePlayDistance] @ [PrePlayFieldPosition] ([PrePlayPossession])[line break]";
		say "Home [HomeScore] - [AwayScore] Away [line break]";
		say "Offence TOs: [OffenceTimeouts] - Defence TOs: [DefenceTimeouts] [line break]";
		say "PC: [PlayCalled] -> PR: [PlayResult] -> FPR: [FinalPlayResult] [line break]";
		say "GYG: [GrossYardsGained] -> NYG: [NetYardsGained] -> GYR: [GrossYardsReturned] -> NYR: [NetYardsReturned] [line break]";
		say "KD: [KickDistance] -> GYR: [GrossYardsReturned] -> NYR: [NetYardsReturned] [line break]";
		

Section - Reporting the player action result


[ REPORT THE RESULT OF THE PLAYER KICKS NOW THAT THE PLAY HAS ACTUALLY BEEN SIMULATED ]

Every turn:
	if PlayCalled is "player kickoff":
		if FinalPlayResult is "kickoff touchback":
			Say "[one of]The ball flies [KickDistance] yards downfield, bouncing through the endzone for a touchback[or]It's a great kick and the ball sails over the return man's head through the endzone for a touchback[or]The return man misjudges his catch and the bounces over him into the endzone for a touchback[or]The return man catches the ball five yards deep in the endzone and elects not to return it, taking a knee for the touchback[or]The return man fields the kick in the endzone and takes a knee for the touchback[or]The return lets the kick bounce past him through the endzone for a touchback[or]The kick rockets through the endzone for a touchback, giving the return man no chance at all[or]The kick pins the return man deep in the endzone and he takes a knee for the touchback[or]The kick flies over the return man's head and out of the endzone for a touchback[or]The kick is pushed even deeper by the wind and flies through the endzone for a touchback[or]The ball sails past the return man and through the endzone for a touchback[at random].";
		Otherwise if FinalPlayResult is "kickoff return":
			Say "[one of]The return man fields it cleanly and sets off upfield for a decent return before being crushed in a tackle by a backup linebacker[or]The return man secures the ball and sprints forward, juking one coverage player before being knocked off his feet by another[or]The return man makes a safe catch and then follows behind a wedge of return players, making a decent gain before being dragged down by the coverage team[or]The return man catches the ball and accelerates up the field, picking up good yards before being upended by a diving tackle[or]The return man catches the ball and sidesteps a speeding coverage player, making a decent gain before he's wrestled down[or]The return man fields the ball cleanly and runs up into the wedge formed by his teammates, picking up good yardage before being drilled by one of the coverage players[or]The return man sees a seam and accelerates into it only to be tripped up at the last moment by one of the coverage players[or]The return man spins away from the first wave of coverage players and zips up the sideline for a nice gain before he's shoved out of bounds[or]The return man runs left and gets to the sideline for a good gain before he's pushed out[or]The return man works off his blockers to weave up the middle of the field for a decent gain before being drilled by one of the coverage team[or]The return man works his way up the field very nicely before he's knocked down hard by a coverage player[or]The return man immediately veers left and tries to get to the sideline but is gang-tackled before he can really get going[or]The return man makes the catch and then tries his luck up the right side but is hit hard by one of the coverage players before he can do much damage[or]The return man stutter steps and makes several players miss before accelerating upfield only to be tripped by a diving coverage player[or]The return man fields the ball and heads right up the middle, making several players miss before he's brought down in a crushing tackle by a linebacker[at random].";
		otherwise if FinalPlayResult is "kickoff tackle":
			say "[one of]The ball is caught [KickDistance] yards downfield by the return man. He sidesteps the first tacklers and burst up a seam in the coverage team. Suddenly he's cutting right in front of you[or]The kick returner fields the ball well and runs up behind the wedge of created by his teammates, finding a lot of space in the coverage. Seemingly instantly he's running right in front of you[or]The return man fields the ball and finds his way to the [one of]left[or]right[at random] sidelines, flying past despairing tackles before cutting back inside. Suddenly he's right there in front of you[or]The return man fields the ball cleanly and dances away from the initial wave of tacklers before turning on the jets and flying up the middle of the field. All too suddenly he's right there in front of you, moving fast[at random]. [one of]You have a split second to make the [bold type]tackle[roman type][or]You have the merest moment to [bold type]tackle[roman type] him[or]There's only a microsecond for you to make the [bold type]tackle[roman type][or]This is your only chance to stop him with a desperation [bold type]tackle[roman type][or]You're all that stands between him and a touchdown, you have to make this [bold type]tackle[roman type][at random]!";
		otherwise if FinalPlayResult is "kickoff return touchdown":
			if KickStatus is "missed tackle":
				say "[one of]You fall flat on your face and the return man prances past you and into the endzone for a touchdown[or]You make an effort, but it's a hopeless one and the return man elludes you easily, high-stepping his way into the endzone for a touchdown[or]You leap at the return man, but he's already several yards behind you when you do so, a second later he's in the endzone celebrating a touchdown[or]You leap sideways and grasp at the return man's legs, but your feeble arms are knocked away and he runs it in for a touchdown[or]You manage to collide violently with the return man, but all the violence is done to you and you end the play lying dazed on the ground while he celebrates his touchdown in the endzone[at random].";
			otherwise if KickStatus is "no tackle":
				say "[one of]Rather than attempting to make a tackle, you watch the return man speed past you and into the endzone for a touchdown[or]Instead of tackling the return man you opt to watch him run past you for an even easier touchdown than necessary[or]You shirk your duties at a member of the team and just let the return man speed past you for a touchdown[or]Instead of going for a tackle, you watch as the return man flies on for the touchdown[at random].";
		otherwise if FinalPlayResult is "kickoff return tackle":
			say "[one of]Miraculously your fingertips somehow clip the return man's heels and he trips and falls down, swearing at his bad luck. You saved the day[or]Against all the odds your shoulder manages to hit the return man's thigh, knocking you hard into the ground but also sending him sprawlig[or]Unbelievably, you make a beautiful form tackle and drive the kick returner into the ground[at random]!";
	otherwise if PlayCalled is "player onside kick":
		if FinalPlayResult is "successful onside kick":
			Say "[successful player onside kick]. The coach waves at you to [bold type]run off the field[roman type].";
		Otherwise if FinalPlayResult is "unsuccessful onside kick":
			Say "[unsuccessful player onside kick]. The coach waves at you to [bold type]run off the field[roman type].";
	otherwise if PlayCalled is "player field goal" and FinalPlayResult is "successful field goal":
		Say "[successful player field goal]. It's good!";
		Say "[line break]";
		say "[italic type]You got 3 points![roman type][line break]";
	otherwise if PlayCalled is "player field goal" and FinalPlayResult is "unsuccessful field goal":
		Say "[unsuccessful player field goal]. It's no good! The coach waves at you to [bold type]run off the field[roman type].";
	otherwise if PlayCalled is "player extra point":
		if FinalPlayResult is "successful extra point":
			Say "[successful player extra point]. It's good!";
			Say "[line break]";
			say "[italic type]You got 1 point![roman type][line break]";
		otherwise if FinalPlayResult is "unsuccessful extra point":
			Say "[unsuccessful player extra point]. It's no good!";
			
	
To say successful player onside kick:
	Say "[one of]The ball slips out of the grasp of the return team and one of your teammates falls on it for a successful onside kick[or]The ball skips away from the return team and one of your players plucks it out of the air for the recovery[or]The ball ends up beneath an enormous pile of players and in the end it turns out your team has it[or]You coverage guys somehow knife through the return unit and grab the ball out of the air[or]Your team miraculously hauls it in[or]The balls skitters through the return team and one of your guys is the first to it[at random]";
	
To say unsuccessful player onside kick:
	Say "[one of]One of the return team players immediately snatches the ball out of the arm and drops to the turf, protecting it carefully before being touched down by one of your teammates[or]The ball is grabbed by one of the return team and he is bundled up in a tackle[or]The ball is chased down by one of the wide receivers on the return team and he picks up a couple of yards before being tackled[or]The ball goes straight to one of the wide receivers on the return team and he secures it before being knocked down by your recovery team[or]One of the wide receivers on the return team falls on the ball as it skitters along and is touched down by one of your guys[at random]";

To say successful player field goal:
	if KickDistance is less than 30:
		Say "[one of]The short field goal flies between the uprights[or]You hit it perfectly, sending it between the goalposts for the field goal[or]You struck it well, sending it easily between the posts[or]You hit the short field goal without any trouble[or]It goes between the uprights with a minimum of fuss[or]You knocked it through without any difficulty[or]You hit a nice kick that sails through the uprights[or]The ball flies just inside [an upright][or]Your effort flies just inside [an upright][or]The ball sails just inside [an upright][at random]";
	otherwise if KickDistance is less than 48:
		Say "[one of]It's a nice kick, sending the ball straight between the uprights[or]The field goal has plenty of distance to spare, heading right between the goalposts[or]You made it just inside [an upright][or]The kick is good, flying inside [an upright][or]The kick is just good, grazing [an upright] on its way through[or]You hit the ball with good distance, bouncing it off [an upright] and in[or]You hit the ball well, sending it ball right through the uprights[or]The ball flies between the uprights[or]You hit it with plenty of distance, squeezing it inside [an upright][at random]";
	otherwise if KickDistance is less than 56:
		Say "[one of]You hits it beautifully, the ball arcing straight between the uprights[or]You nailed the kick, sending the ball between the uprights[or]You made it look easy, splitting the uprights with a beautiful kick[or]You hit it with great power, squeezing the ball just inside [an upright][or]You hit a strong kick, getting it just inside [an upright][or]You hit it well, just getting it inside [an upright][at random]";
	otherwise:
		Say "[one of]You crushed it, launching the ball and clearing the cross bar for the long-range field goal[or]You boomed a huge kick, squeezing it inside [an upright][or]You nailed the kick, sending the ball just over the top of the cross bar[at random]";
	
To say unsuccessful player field goal:
	if KickDistance is less than 30:
		Say "[one of]The seemingly easy kick sails wide left[or]The kick is a bad one, bouncing off [an upright] and going wide[or]The kick hits [an upright] and bounces down into the endzone[or]The kick is wide, flying outside [an upright] by a several feet[or]The kick is just wide, grazing [an upright] on its way[or]The simple seeming kick goes wide of [an upright][or]The ball hits [an upright] and bounces away[or]The ball sails wide[at random]";
	otherwise if KickDistance is less than 48:
		Say "[one of]The kick isn't convincing and sails wide of [an upright][or]It bounces it off [an upright][or]You hit a nice kick, but it bounces out off [an upright][or]The kick sails wide right[or]You didn't connect well enough and the ball sails wide of [an upright][at random]";
	otherwise if KickDistance is less than 56:
		Say "[one of]You didn't quite get hold of it well enough and the ball falls short of the cross bar[or]You sliced it and the ball sails wide of [an upright][or]You didn't hit it quite right and it sails wide[or]You nailed a nice-looking kick, but it bounces straight off [an upright] and back into the endzone[or]You must have mishit the ball and it flies wide of [an upright][at random]";
	otherwise:
		Say "[one of]You didn't get enough power on the kick and the ball falls short of the cross bar[or]You didn't get hold of it well enough and it falls short[or]You didn't have the leg for such a big kick and it falls short[or]You hit an amazingly powerful kick, but it sails wide of [an upright][at random]";
	
To say successful player extra point:
	Say "[one of]You make the routine kick straight between the uprights[or]You slot it between the goal posts easily[or]You knock it through without any difficulty[or]You mishit it slightly, but squeeze it inside [an upright][or]You hit it well enough for it to get inside [an upright][or]You make the easy kick for the extra point[or]You make the extra point kick without any difficulty[or]You make the kick with no trouble at all[or]You almost shank the ball, bouncing the extra point in off [an upright][or]You knock it through between the uprights[or]You make the kick look as easy as it is, hitting the extra point[or]You knock through the extra point without any trouble[or]You slot it between the goal posts easily[or]You knock it through for the extra point[at random]";
	
To say unsuccessful player extra point:
	Say "[one of]Somehow you bounce the kick off [an upright][or]Somehow the kick goes wide, grazing the outside of [an upright][or]The kick is blocked at the line by a leaping defensive lineman[or]The easy kick inexplicably sails wide of [an upright][at random]";



Chapter - Football simulation, The clock

[ RUNNING / STOPPED CLOCK ]

Every turn:
	if (FinalPlayResult is "gain") or FinalPlayResult is "first down":
		[ CLOCK STOPS ON OUT OF BOUNDS AT ENDS OF HALVES ]
		Let Rand be a random number between 0 and 100;
		if (Q is 2 and T is less than 120) or (Q is 4 and T is less than 300):
			if Rand is less than 90 and PlayCalled is "run":
				Change Clock to "running";
			otherwise if PlayCalled is "run":
				Change Clock to "stopped";
			if Rand is less than 80 and PlayCalled is "pass":
				Change Clock to "running";
			otherwise if PlayCalled is "pass":
				Change Clock to "stopped";
		otherwise:
			Change Clock to "running";
	otherwise if FinalPlayResult is "no gain" or FinalPlayResult is "sack" or FinalPlayResult is "loss" or FinalPlayResult is "take a knee":
		Change Clock to "running";
	otherwise:
		Change Clock to "stopped";
	



[ UPDATE PRE SNAP TIME ]

Every turn:
	if Clock is not "running":
		Change PT to 0;
	otherwise if FinalPlayResult is "false start":
		Change PT to 0;
	otherwise if PlayCalled is "run" or PlayCalled is "pass" or PlayCalled is "punt" or PlayCalled is "field goal" or (PlayCalled is "player field goal" and KickStatus is "kicked") or PlayCalled is "take a knee":
		if OffenceMode is "hurry up":
			Change PT to a random number between 10 and 15;
		otherwise if PlayCalled is "take a knee":
			Change PT to 40;
		otherwise if OffenceMode is "eat the clock":
			Change PT to a random number between 35 and 40;
		otherwise:
			Change PT to a random number between 28 and 40;
	otherwise:
		Change PT to 0;
		
	


[ UPDATE POST SNAP TIME ]

Every turn:
	if FinalPlayResult is "false start":
		Change PT to 0;
	otherwise if PlayCalled is "run" or PlayCalled is "pass":
		[ SNAP TIME ]
		Increase PT by a random number between 3 and 4;
		if FinalPlayResult is "incomplete":
			[ MISS TIME ]
			Increase PT by 2;
		otherwise if GrossYardsGained is less than 30:
			[ SHORTISH PASS TIME ]
			Increase PT by a random number between 2 and 4;
		otherwise:
			[ LONG PASS TIME ]
			Increase PT by a random number between 3 and 8;	
		if FinalPlayResult is "interception" or FinalPlayResult is "interception return touchdown":
			if NetYardsReturned is less than 20:
				[ SHORTIST INTERCEPTION RETURN ]
				Increase PT by a random number between 2 and 5;
			otherwise:
				[ LONG INTERCEPTION RETURN ]
				Increase PT by a random number between 5 and 6;
		otherwise if FinalPlayResult is "fumble" or FinalPlayResult is "fumble return touchdown":
			if NetYardsReturned is less than 20:
				[ SHORTISH FUMBLE RETURN ]
				Increase PT by a random number between 2 and 5;
			otherwise:
				[ LONG FUMBLE RETURN ]
				Increase PT by a random number between 5 and 6;
	otherwise if PlayCalled is "kickoff" or (PlayCalled is "player kickoff" and KickStatus is "kicked"):
		Increase PT by a random number between 5 and 8;
		if GrossYardsReturned is less than 30:
			[ SHORT KICKOFF RETURN ]
			Increase PT by 4;
		otherwise:
			[ LONG KICKOFF RETURN ]
			Increase PT by a random number between 6 and 8;	
	otherwise if PlayCalled is "safety kickoff":
		Increase PT by a random number between 5 and 8;
		if GrossYardsReturned is less than 30:
			[ SHORT KICKOFF RETURN ]
			Increase PT by 3;
		otherwise:
			[ LONG KICKOFF RETURN ]
			Increase PT by 6;
	otherwise if PlayCalled is "onside kick" or (PlayCalled is "player onside kick" and KickStatus is "kicked"):
		[ ONSIDE KICK RETURN TIME ]
		Increase PT by a random number between 4 and 8;
	otherwise if PlayCalled is "punt":
		Increase PT by a random number between 6 and 8;
		if GrossYardsReturned is less than 30:
			[ SHORT PUNT RETURN ]
			Increase PT by 4;
		otherwise:
			[ LONG PUNT RETURN ]
			Increase PT by 7;
	otherwise if PlayCalled is "field goal" or (PlayCalled is "player field goal" and KickStatus is "kicked"):
		Increase PT by a random number between 5 and 6;
	otherwise if PlayCalled is "extra point":
		Increase PT by 0;
	otherwise if PlayCalled is "take a knee":
		Increase PT by 2;
	


[ UPDATE TIME FOR MAJOR TIME SHIFTS ]

Section - Major time shifts

Every turn:
	if PlayCalled is "start next quarter":
		Increase Q by 1;
		Change T to 900;
		Change PT to 0;
		Change Clock to "start of quarter";
		Change DrinksHad to 0;		
	otherwise if PlayCalled is "start second half":
		Increase Q by 1;
		Change T to 900;
		Change PT to 0;
		Change PrePlayPossession to -1;
		Change PostPlayPossession to -1;
		Change PrePlayDown to 1;
		Change PostPlayDown to 1;
		Change PrePlayDistance to 0;
		Change PostPlayDistance to 0;
		Change PrePlayFieldPosition to 30;
		Change PostPlayFieldPosition to 30;
		Change Clock to "start of second half";
		Change OffenceTimeouts to 3;
		Change DefenceTimeouts to 3;
		Change TwoMinuteWarningGiven to "false";
		Change DrinksHad to 0;
		Move the player to the sidelines;
		Move the offensive unit to the sidelines;
		Move the defensive unit to the sidelines;
		Move the punter to the sidelines;
		Move the special teams unit to the sidelines;
	otherwise if PlayCalled is "start overtime":
		Increase Q by 1;
		Change T to 900;
		Change PT to 0;
		Change PrePlayPossession to -1;
		Change PostPlayPossession to -1;
		Change PrePlayDown to 1;
		Change PostPlayDown to 1;
		Change PrePlayDistance to 0;
		Change PostPlayDistance to 0;
		Change PrePlayFieldPosition to 30;
		Change PostPlayFieldPosition to 30;
		Change OffenceTimeouts to 2;
		Change DefenceTimeouts to 2;		
		Change Clock to "start of overtime";
		Change DrinksHad to 0;	
		Move the player to the sidelines;	
		Move the offensive unit to the sidelines;
		Move the defensive unit to the sidelines;
		Move the punter to the sidelines;
		Move the special teams unit to the sidelines;		
	otherwise if PlayCalled is "two minute warning":
		Change FinalPlayResult to "two minute warning";
		Change Clock to "end of two minute warning";
	
	

[ UPDATE THE TIME TAKEN FOR THE PLAY ]

Every turn:
	Change T to T - PT;
	if T <= 0:
		Change T to 0;
		if Q is 1 or Q is 3:
			if FinalPlayResult is not "touchdown" and FinalPlayResult is not "interception return touchdown" and FinalPlayResult is not "fumble return touchdown":
				Change Clock to "end of quarter";
		otherwise if Q is 2:
			if FinalPlayResult is not "touchdown" and FinalPlayResult is not "interception return touchdown" and FinalPlayResult is not "fumble return touchdown":
				Change Clock to "end of half";
		otherwise if Q is 4:
			if FinalPlayResult is not "touchdown" and FinalPlayResult is not "interception return touchdown" and FinalPlayResult is not "fumble return touchdown":		
				if HomeScore is AwayScore:
					Change Clock to "end of regulation";
				otherwise:
					Change Clock to "end of game";		
		otherwise if Q is 5:
			Change Clock to "end of game";
	


[ TWO MINUTE WARNING ]

Every turn:
	If Q is 2 or Q is 4:
		if TwoMinuteWarningGiven is "false" and T <= 120:
			Change Clock to "two minute warning";
			Change TwoMinuteWarningGiven to "true";
		
	
	

[ TIMEOUTS ]

Every turn:
	[ say "Checking timeout: Clock is [Clock], T is [T], PrePlayDown is [PrePlayDown] and Q is [Q]"; ]
	if Clock is "running" and T is less than 180 and PrePlayDown is not 4 and (Q is 2 or Q is 4):
		if OffenceMode is "hurry up" and OffenceTimeouts is not 0:
			Change Clock to "offence timeout";
			Decrease OffenceTimeouts by 1;
			Change TimeoutReport to "The offense calls a timeout";
		otherwise if OffenceMode is "eat the clock" and DefenceTimeouts is not 0:
			Change Clock to "defence timeout";
			Decrease DefenceTimeouts by 1;
			Change TimeoutReport to "The defense calls a timeout";
		




[ REPORT PENALTY WHISTLES ]

Every turn:
	if FinalPlayResult is "pass interference" or FinalPlayResult is "holding" or FinalPlayResult is "false start" or FinalPlayResult is "offsides":
		Change RefereeReport to "[one of]You hear the referee's indistinct voice announce a penalty over the stadium speakers[or]You hear the referee's distorted voice over the PA announce a penalty[or]You vaguely hear a penalty of some sort being announced by the referee[or]You hear the garbled sounds of the referee announcing a penalty[or]You indistinctly hear a penalty announced over the PA system[or]A penalty of some kind is announced over the PA, but you can't make out what it is[or]A penalty is announced by the referee, but you can't hear what it was very clearly[or]The referee announces a penalty over the PA system, but the crowd noise drowns it out[or]The referee's distorted voice announces a penalty over the PA[at random]";
		
[ REPORT TIME-WHISTLES ]			
		
Every turn:
	if Clock is "end of regulation":
		Change RefereeReport to "The referee blows his whistle to signal the end of regulation time";
	otherwise if Clock is "end of quarter":
		Change RefereeReport to "The referee blows his whistle to signal the end of the quarter";
	otherwise if Clock is "end of half":
		Change RefereeReport to "The referee blows his whistle to signal the end of the half";
	otherwise if Clock is "two minute warning":
		Change RefereeReport to "The referee blows his whistle to signal the two minute warning";
	otherwise if Clock is "end of overtime":
		Change RefereeReport to "The referee blows his whistle to signal the end of overtime";
	


[ REPORT THE CLOCK ]

Every turn:
	if VerboseMode is "VERBOSE":
		say "Clock is: [Clock]";
	



Chapter - The statistics

[ STATISTICS ]

Every turn:
	if PrePlayPossession is 1:
		Increase HP by 1;
	otherwise:
		increase AP by 1;
	if (PlayCalled is "pass" and FinalPlayResult is not "sack") or PlayCalled is "spike the ball":
		if PrePlayPossession is 1:
			Increase HPA by 1;
			Increase HPY by NetYardsGained;
			if FinalPlayResult is "interception" or FinalPlayResult is "interception return touchdown":
				Increase HPI by 1;
			otherwise if FinalPlayResult is not "incompletion" and FinalPlayResult is not "spike the ball":
				Increase HPC by 1;
				if FinalPlayResult is "touchdown":
					Increase HPTD by 1;
		otherwise:
			Increase APA by 1;
			Increase APY by NetYardsGained;
			if FinalPlayResult is "interception" or FinalPlayResult is "interception return touchdown":
				Increase API by 1;
			otherwise if FinalPlayResult is not "incompletion" and FinalPlayResult is not "spike the ball":
				Increase APC by 1;
				if FinalPlayResult is "touchdown":
					Increase APTD by 1;
	otherwise if PlayCalled is "run":
		if PrePlayPossession is 1:
			Increase HRA by 1;
			Increase HRY by NetYardsGained;
			if FinalPlayResult is "touchdown":
				Increase HRTD by 1;
		otherwise:
			Increase ARA by 1;
			Increase ARY by NetYardsGained;
			if FinalPlayResult is "touchdown":
				Increase ARTD by 1;
	otherwise if (PlayCalled is "field goal" or PlayCalled is "player field goal") and FinalPlayResult is "successful field goal":
		if PrePlayPossession is 1:
			Increase HFGA by 1;
			Increase HFGM by 1;
		otherwise:
			Increase AFGA by 1;
			Increase AFGM by 1;
	otherwise if (PlayCalled is "field goal" or PlayCalled is "player field goal") and (FinalPlayResult is "unsuccessful field goal" or FinalPlayResult is "miskicked field goal"):
		if PrePlayPossession is 1:
			Increase HFGA by 1;
		otherwise:
			Increase AFGA by 1;
	otherwise if (PlayCalled is "extra point" or PlayCalled is "player extra point") and FinalPlayResult is "successful extra point":
		if PrePlayPossession is 1:
			Increase HXPA by 1;
			Increase HXPM by 1;
		otherwise:
			Increase AXPA by 1;
			Increase AXPM by 1;
	otherwise if (PlayCalled is "extra point" or PlayCalled is "player extra point") and (FinalPlayResult is "unsuccessful extra point" or FinalPlayResult is "miskicked extra point"):
		if PrePlayPossession is 1:
			Increase HXPA by 1;
		otherwise:
			Increase AXPA by 1;
	otherwise if PlayCalled is "punt":
		if PrePlayPossession is 1:
			Increase HPT by 1;
		otherwise:
			Increase APT by 1;
		
	


Chapter - Reporting half time

Every turn:
	if PlayCalled is "start second half":
		say "[the halftime speech]";
		move the player to the sidelines;
	otherwise if PlayCalled is "start overtime":
		say "[the overtime speech]";
		move the player to the sidelines;

		



Part - Reporting the play


Section - To say the play report

To say the play report:
	if FinalPlayResult is:
		-- "false start":
			say "[false start]";
		-- "offsides":
			say "[offsides]";
		-- "pass interference":
			say "[pass interference]";
		-- "holding":
			say "[holding]";
		-- "kickoff return":
			say "[kickoff return]";
		-- "kickoff touchback":
			say "[kickoff touchback]";
		-- "safety kickoff return":
			say "[safety kickoff]";
		-- "successful onside kick":
			say "[successful onside kick]";
		-- "unsuccessful onside kick":
			say "[unsuccessful onside kick]";
		-- "punt":
			say "[punt return]";
		-- "punt touchback":
			say "[punt touchback]";
		-- "sack":
			say "[sack]";						
		-- "loss":
			say "[loss]";
		-- "no gain":
			say "[no gain]";
		-- "incompletion":
			say "[incompletion]";			
		-- "gain":
			say "[gain]";
		-- "first down":
			say "[first down]";
		-- "touchdown":
			say "[touchdown]";
		-- "interception":
			say "[interception]";
		-- "interception return touchdown":
			say "[interception return touchdown]";
		-- "fumble":
			say "[fumble]";
		-- "fumble return touchdown":
			say "[fumble return touchdown]";
		-- "turnover on downs":
			say "[turnover on downs]";
		-- "safety":
			say "[safety]";
		-- "successful field goal":
			say "[successful field goal]";
		-- "unsuccessful field goal":
			say "[unsuccessful field goal]";
		-- "successful extra point":
			say "[successful extra point]";
		-- "unsuccessful extra point":
			say "[unsuccessful extra point]";	
		-- "successful two point conversion":
			say "[successful two point conversion]";		
		-- "unsuccessful two point conversion":
			say "[unsuccessful two point conversion]";	
		-- "spike the ball":
			say "[spike the ball]";
		-- "take a knee":
			say "[take a knee]";	
		-- "start first half":
			say "[start first half]";
		-- "start second half":
			say "[start second half]";
		-- "start overtime":
			say "[start overtime]";
		-- "start next quarter":
			say "[start next quarter]";
		-- "post two minute warning":
			say "[post two minute warning]";
			


Section - Play report helpers

To say the play report prefix:
	Say "[the view prefix] [the subject possessive]";

To say the view prefix:
	Say "[one of]On the field,[or]You watch as[or]Out on the field,[or]As you watch,[at random]";
	
To say the subject possessive:
	Say "[if PrePlayPossession is 1][one of]your team's[or]your[or]the[at random][otherwise][one of]the opposition team's[or]their[or]the opposition[or]the other team's[or]the away team's[or]the opponent's[or]the opposing[or]the opposing team's[at random][end if]";

To say one of the offensive linemen:
	Say "the [one of]left tackle[or]right tackle[or]left guard[or]right guard[or]center[at random]"
	
To say one of the defensive linemen:
	Say "the [one of]left defensive end[or]right defensive end[or]one of the defensive tackles[at random]"

To say one of the linebackers:
	Say "[one of]an outside linebacker[or]an inside linebacker[at random]";

To say one of the defensive players:
	Say "[one of][one of the defensive linemen][or]a cornerback[or]an inside linebacker[or]an outside linebacker[or]the free safety[or]the strong safety[at random]";

To say one of the pass receivers:
	Say "[one of]the running back[or]the fullback[or]the tight end[or]a wide receiver[or]a wide receiver[or]a wide receiver[at random]";
	
To say one of the backfield pass receivers:
	Say "[one of]the running back[or]the running back[or]the running back[or]the fullback[at random]";

To say one of the short pass receivers:
	Say "[one of]the tight end[or]the running back[or]the fullback[at random]";

To say one of the deep pass receivers:
	Say "[one of]the tight end[or]a wide receiver[or]a wide receiver[or]a wide receiver[or]a wide receiver[or]a wide receiver[at random]";

To say one of the pass rushers:
	Say "[one of]a defensive end[or]an outside linebacker[or]an inside linebacker[or]a blitzing corner[or]a blitzing safety[at random]";

To say one of the pass defenders:
	Say "[one of]a cornerback[or]the free safety[or]the strong safety[or]a linebacker[at random]";
	
To say one of the deep pass defenders:
	Say "[one of]a cornerback[or]the free safety[or]the strong safety[at random]";



Section - Play reports

[ PENALTIES ]

To say false start:
	Say "[the play report prefix] quarterback [one of]stands over the center to start the play[or]gets ready for the snap[or]stands over center[or]barks out the snap count[or]stands over center and studies the defense[or]calls out the snap count[or]tries to call an audible[at random] [one of]only to see [one of the offensive linemen] move early[or]but [one of the offensive linemen] twitches and draws a false start penalty[or]but [one of the offensive linemen] moves and gets a penalty[or]but [one of the offensive linemen] shifts and draws a penalty[or]but [one of the offensive linemen] jumps and is called for a false start[at random]";

To say holding:
	Say "[run play], [one of]but the play is called back because [one of the offensive linemen] was holding his man[or]but the play is canceled out by a holding call on [one of the offensive linemen][or]but it's called back for holding[or]but the play is negated by a holding call[or]but a flag is thrown for a holding penalty and the play is called back[or]but [one of the offensive linemen] is called for holding[or]but there's a holding call on [one of the offensive linemen][at random]";

To say offsides:
	Say "[the play report prefix] quarterback [one of]sees [one of the defensive linemen] jump offside[or]sees a linebacker lined up offside[or]catches one of the outside linebackers moving over the line too soon[or]sees [one of the defensive linemen] lined up offside[or]sees a cornerback lined up offside[or]sees [one of the defensive linemen] move early[at random] [one of], calls for the snap, and throws a quick pass[or]and snaps the ball to throw a quick pass[or]and throws a fast pass[or]and throws a pass deep downfield[or]tries to hit a wide receiver on a deep route[at random], [one of]but it falls incomplete[or]but it's overthrown[or]but it skips off the turf incomplete[or]but the ball is knocked away by a cornerback[or]but it doesnt find its man[or]but the ball is dropped[at random]";

To say pass interference:
	if NetYardsGained is less than 10:
		Say "[the play report prefix] quarterback [short route], but [one of]it's incomplete[or]it's dropped[or]it's incomplete[at random] because [one of]the receiver is mugged by his defender[or]the defender is draped all over the receiver[or]the defender shoved the receiver[at random]. The nearby official throws his flag for pass interference";
	otherwise if NetYardsGained is less than 30:
		Say "[the play report prefix] quarterback [medium route], but [one of]it's incomplete[or]it's dropped[or]it's incomplete[at random] because [one of]the receiver is mugged by his defender[or]the defender is draped all over the receiver[or]the defender shoved the receiver[at random]. The nearby official throws his flag for pass interference";
	otherwise:
		Say "[the play report prefix] quarterback [deep route], but [one of]it's incomplete[or]it's dropped[or]it's incomplete[at random] because [one of]the receiver is mugged by his defender[or]the defender is draped all over the receiver[or]the defender shoved the receiver[at random]. The nearby official throws his flag for pass interference";
			

[ STANDARD PLAYS ]

[ RUNS ]

To say run type:
	Say "[one of]charges into the line[or]cuts toward the left side of the line[or]makes a sharp cut into the line[or]cuts to the right side of the line[or]crashes into the middle of the line[or]thunders off-tackle[or]dances to the outside[or]dashes for a gap in the line[or]pushes into a space on the right side of the line[or]darts left[or]accelerates into the line[or]jukes left and then cuts right[or]heads for the corner[or]sweeps right[or]sweeps left[or]follows the fullback into the line[or]follows a big block from the tight end[or]accelerates left[or]finds a small opening on the right side[at random]";
	
To say loss run:
	Say "[one of]but is dragged down for a loss[or]but is knocked down before he can make any ground[or]but has to dodge back from a tackle and is blindsided by the [one of the defensive players][or]but has to cut in the opposite direction and is pulled down for a loss[or]but stumbles and is upended for a loss[or]but trips and loses yards[or]but falls down in the backfield after [one of the offensive linemen] steps on his foot[or]but is bundled up in the backfield by [one of the defensive linemen][or]but is wrapped up and dropped for a loss by [one of the defensive players][or]but can't make any ground and loses yards after being tripped by [one of the defensive players][or]but is driven backwards by [one of the defensive linemen][or]but is pushed back in a crushing tackle by [one of the defensive linemen][at random]";	

To say no gain run:
	Say "[one of]but is stuffed for no gain by [one of the defensive linemen][or]but can't make any ground before being knocked over by [one of the defensive linemen][or]but is crushed right on the line by [one of the defensive players][or]but stumbles and crashes down at the feet of the offensive linemen[or]but is met in the hole by a linebacker who drives him into the ground[or]but is knocked off his feet by [one of the linebackers] for no gain[or]but is dragged down by [one of the defensive linemen] for no gain[or]but is wrapped up in a tough tackle by [one of the linebackers] immediately[or]but is knocked down right away by a crushing hit from [one of the linebackers][at random]";

To say short run:
	Say "[one of]then bulldozes forward for a couple of yards[or]and bursts through strongly before being met head on by [one of the defensive players] in a crunching tackle[or]and seems to find daylight but is knocked down by [one of the defensive players] after a short gain[or]then spins directly into the path of [one of the defensive players] and is brought down for a couple of yards gain[or]then hesitates a moment too long and is gang-tackled by the linebackers after making a couple of yards[or]before trying to make one too many moves in the open field and being pulled down from behind by [one of the defensive linemen] for a short gain[or]then moves upfield before being upended by [one of the linebackers] after a short gain[or]then spins and picks up a couple of yards before being knocked off his feet by [one of the defensive players][or]then heads forward a few steps before being driven sideways by [one of the linebackers] in a huge tackle[at random]";
	
To say medium run:
	Say "[one of]and manages to find a nice seam before being brought down by [one of the defensive players][or], spins out of a tackle, and stumbles forward a few more yards before being knocked down[or]and bursts through for a strong gain before being closed down by [one of the pass defenders][or]jukes his way past [one of the defensive linemen]and rampages into the defensive backfield before being tripped by [one of the pass defenders][or]bulldozes through [one of the linebackers] and careens off [one of the pass defenders] before being knocked down by the strong safety[or]and bursts upfield for a nice gain before being caught from behind by [one of the linebackers][or]and makes a nice move between two tacklers before being wrapped up by [one of the linebackers][or]and zips up a seam in the defense before [one of the pass defenders] wraps him up around the legs[or]and bounces [one of the pass defenders] off his shoulder, and spins and jukes his way to a nice gain before getting hammered by [one of the linebackers][or]and flashes up the field before being tripped by [one of the pass defenders][or]thens spins out of [one of the defensive linemen]'s tackle and picks up good yards before he is crushed by the strong safety[at random]";
	
To say big run:
	Say "[one of]and spins out of a tackle, racing to the sidelines and picking up a huge gain before being caught by [one of the pass defenders][or]and bursts up the middle, juking [one of the linebackers] and racing on for more yards before being tripped by [one of the pass defenders][or]and bursts untouched through the line and is a long way down the field before he's tripped by [one of the pass defenders][or]races past the linebackers before they know what's happened and is only wrestled down by [one of the pass defenders][or]and races outside, sidestepping [one of the pass defenders] before being caught from behind by [one of the linebackers][or]and suddenly accelerates through the first line of defense, crashing through one tackle by [one of the pass defenders] before finally being brought down a long way upfield[or]and stiff-arms [one of the linebackers] out of the way before racing to the outside and almost going all the way before being dragged down by [one of the pass defenders][or]and barrels over [one of the linebackers] before rushing a long way into the defensive secondary and being knocked down by [one of the pass defenders][at random]";
	
To say touchdown run:
	if NetYardsGained is less than 5:
		Say "[one of]and bulls his way through a [one of the linebackers] into the endzone for a touchdown[or]and steps lightly into the endzone for a touchdown[or]and spins past [one of the defensive linemen] before walking into the endzone for a touchdown[or]and walks untouched into the endzone for a touchdown[or]and stiff-arms a blitzing cornerback before stepping into the endzone for a touchdown[or]and dives into the endzone for a touchdown[or]and jukes [one of the linebackers] before barreling over a cornerback into the endzone[or]and drags a would-be tackler into the endzone for a touchdown[or]and high-steps into the endzone for a touchdown[or]and lowers his shoulder on [one of the pass defenders], falling forward into the endzone for a touchdown[at random]";
	otherwise if NetYardsGained is less than 20:
		Say "[one of]and leaps over a diving linebacker before spinning away from [one of the pass defenders] and fighting his way through a tackle into the endzone[or]and bursts through a gap in the defense, stiff-arming [one of the linebackers] out of his way and plunging into the endzone[or]and spots a weakness in the defense, cutting sharply past [one of the linebackers] and carrying [one of the pass defenders] on his back into the endzone[or]and cuts right in front of [one of the linebackers] to make him miss before racing down the sideline for a touchdown[at random]";
	otherwise if NetYardsGained is less than 40:
		Say "[one of]and finds a seam in the defense, plowing over [one of the pass defenders] for the touchdown[or]and side-steps [one of the linebackers] before racing to the sideline and into the endzone for a touchdown[or], barrels through an arm-tackle and stiff-arms [one of the linebackers] before rambling the rest of the way for a touchdown[or]and bursts through untouched before spinning away from [one of the pass defenders] and high-stepping into the endzone for a touchdown[or]then stiff-arms [one of the linebackers] and races past the rest of the defense for a touchdown[or]and races into open field, bursting through the despairing tackle of [one of the pass defenders] into the endzone[at random]";
	otherwise:
		Say "[one of]and suddenly finds a huge hole in the defense and is off to the races against [one of the deep pass defenders], beating him to the endzone for a touchdown[or]and slips through a gap between two defenders before bursting through a desperate tackle in the defensive secondary and racing the rest of the way to the endzone for a touchdown[or]and makes a couple of defenders miss before flashing up the sidelines untouched for a long touchdown[or]and busts through a tackle from [one of the linebackers], jukes [one of the pass defenders] and accelerates away down the field to the endzone for a touchdown[at random]";

To say run play:
	if PlayResult is:
		-- "no gain":
			Say "[the play report prefix] running back [run type] [no gain run]";
		-- "gain":
			if PlayDistance is:
				-- "short":
					Say "[the play report prefix] running back [run type] [short run]";
				-- "medium":
					Say "[the play report prefix] running back [run type] [medium run]";
				-- "big":
					Say "[the play report prefix] running back [run type] [big run]";
		-- "loss":
			Say "[the play report prefix] running back [run type] [loss run]";
	


[ PASSES ]

To say backfield route:
	Say "[one of]dumps it off to[or]throws a screen to[or]throws a flare to[or]sets up a screen to[at random] [one of the backfield pass receivers]";
	
To say short route:
	Say "[one of]hits[or]throws[or]rifles the ball[or]zips it[or]passes[or]rifles the ball[at random] [one of]on a short crossing route[or]on a flare[or]on a drag route[or]on a curl[or]through a gap in the zone[or]on a screen[at random] to [one of the short pass receivers]";
	
To say medium route:
	Say "[one of]fires[or]zips[or]passes[or]throws[or]rifles[or]arcs[at random] the ball [one of]on an out[or]on a post route[or]on a crossing route[or]on a curl[at random] to [one of the deep pass receivers]";
	
To say deep route:
	Say "[one of]lofts[or]fires[or]zips[or]floats[or]hurls[or]lasers[at random] the ball [one of]on a streak on the outside[or]on a deep post[or]on a deep out[or]on a corner route[at random] to [one of the deep pass receivers]";

To say loss pass:
	Say "[backfield route] [one of]only to see him get dropped immediately by [one of the defensive linemen][or]but he's knocked down immediately by [one of the linebackers] who is covering him[or]but he's wrapped up in a strong tackle by [one of the defensive linemen][or]but he's tackled as soon as he catches it by [one of the linebackers][or]but he's tripped by a [one of the pass rushers] before he can get out of the backfield[or]but he slips and is whacked by [one of the defensive linemen][or]but he is levelled by a blitzing linebacker before he can take a step[or]but he's swung down by [one of the linebackers]before he can make it out of the backfield[at random]";	

To say no gain pass:
	Say "[backfield route] [one of]only to see him get dropped for no gain by [one of the defensive linemen][or]but he's met in a crushing tackle by [one of the linebackers] right on the line of scrimmage[or]but one of the linebackers knocks him down before he can even make a yard[or]but he's dragged down by his free arm by [one of the defensive linemen] before he can get past the line of scrimmage[or]but he goes down under a pile of defenders before he can turn upfield[or]but he's knocked backwards at the line by a blitzing linebacker[or]but he is tripped by [one of the pass rushers] and falls down at the line of scrimmage for no gain[or]but he is nailed by [one of the pass rushers] at the line and goes down hard[or]but he's bundled up by [one of the linebackers] at the line of scrimmage for no gain[or]but he's stuffed at the line by [one of the defensive linemen][at random]";	

To say short pass:
	Say "[one of][backfield route] [one of]but he doesn't make it much past the line of scrimmage before being leveled by [one of the linebackers][or]and he bursts out of the backfield at speed but is crushed by [one of the linebackers] before he can turn it into something big[or]and he cuts nicely in the backfield to avoid a tackler and makes a few yards before being hit fiercly by [one of the pass defenders][or]but he doesn't make more than a couple of yards before being tracked down and whacked by [one of the linebackers][or]but he's caught from behind after gaining a couple of yards by [one of the defensive linemen][or]but he can't quite get on track before [one of the linebackers] upends him[or]but he slips and then is knocked down by [one of the defensive linemen] before he makes much ground[at random][or][short route] [one of]but he's hit immediately by [one of the pass defenders][or]but he's wrapped up by [one of the linebackers] before he can take a step[or]but he's leveled by [one of the pass defenders] before he can make a move[or]but he doesn't have time to get moving before he's dragged down by [one of the pass defenders][or]but the man matched up with him makes a quick and sure tackle[or]but he's driven into the ground by [one of the linebackers] in a tough tackle[or]but he's hit hard by [one of the pass defenders] as soon as he makes the catch[at random][at random]";	
	
To say medium pass:
	Say "[one of][backfield route] [one of]who slips a tackle and zips upfield for a nice gain before being knocked down by [one of the pass defenders][or]who makes a man miss in the backfield and picks up a bunch of yards before [one of the pass defenders] wraps him up[or]who jukes [one of the linebackers] and finds some space to run before [one of the deep pass defenders] wraps him up around the legs[or]who spins away from [one of the defensive linemen] and bursts up the middle of the field until [one of the linebackers] drags him down[at random][or][short route] [one of]who side-steps [one of the pass defenders] and picks up a few extra yards before being brought down by the free safety[or]who cuts across the middle of the field for more yards before being hit hard by [one of the linebackers][or]who spins away from the man matched up with him and picks up bonus yards before [one of the pass defenders] knocks him down[or]who darts forward for a few more yards before being tripped up by [one of the pass defenders][at random][or][medium route] [one of]who is hit immediately and dropped to the turf[or]who is crushed by [one of the pass defenders] but manages to hold onto the ball for a nice gain[or]who almost breaks away from [one of the linebackers] in a desperate struggle but is dragged down in the end[or]who slips the tackle of [one of the linebackers] but is then knocked down hard by [one of the deep pass defenders] right away[or]who is upended right away by [one of the pass defenders][at random][at random]";	
	
To say big pass:
	Say "[one of][backfield route] [one of]who slips a tackle by [one of the defensive linemen] and then races past the linebackers into the open field, picking up huge yards before [one of the deep pass defenders] is able to catch him and bring him down[or]who stays behind a wall of blockers before bursting through one tackler and stumbling and being dragged down by [one of the deep pass defenders] after a massive gain[or]who follows a convoy of blockers and goes deep down the field before [one of the deep pass defenders] is able to tackle him[or]who races out of the backfield and up the sidelines for a huge gain before being hit in a crushing tackle by [one of the deep pass defenders][at random][or][medium route] [one of]who turns upfield and makes a couple of tacklers miss, picking up great yards before [one of the deep pass defenders] wrestles him down[or]who makes his man miss badly and races for huge extra yards before [one of the deep pass defenders] knocks him down[or]who side-steps a diving defender and picks up a lot of extra yards before [one of the pass defenders] crushes him in a tough tackle[at random][or][deep route] [one of]who collects the ball beautifully and makes one man miss before being hit by [one of the deep pass defenders][or]who makes a stunning diving catch before getting up only to be crushed by [one of the deep pass defenders][or]who bobbles the ball briefly but hauls it in and is knocked down by [one of the deep pass defenders][or]who is wrapped up right away by [one of the deep pass defenders][or]who is hit viciously by [one of the deep pass defenders] as he dives and makes the catch[at random][at random]";	
	
To say touchdown pass:
	if NetYardsGained is less than 5:
		Say "[one of][short route] in the endzone for a quick hitting touchdown[or][short route] who snatches the ball out of the air in the endzone for the touchdown[or][short route] in the endzone for the score[or][short route] who grabs the ball and holds on for the touchdown[or][backfield route] who jukes out of the backfield and dives in for the touchdown[or][backfield route] who slips a tackle from [one of the defensive linemen] and walks it in for the touchdown[or][backfield route] who burst through a tackle from [one of the linebackers] and dives into the endzone for a touchdown[at random]";
	otherwise if NetYardsGained is less than 15:
		Say "[one of][backfield route] who jukes [one of the linebackers] out of his shoes and races down the sidelines for a touchdown[or][backfield route] who bursts up field and barrels over [one of the linebackers] and into the endzone for a touchdown[or][short route] who makes a nice catch and then steps into the endzone for a touchdown[or][short route] who snatches the ball and makes his man miss before diving into the endzone past [one of the pass defenders] for a touchdown[or][medium route] for an easy touchdown[or][medium route] who grabs the ball in traffic for a touchdown[or][medium route]who snatches the ball out of the air for a touchdown[or][medium route] who makes a diving catch for a touchdown[or][medium route] who just manages to keep his feet in bounds for the touchdown[at random]";
	otherwise if NetYardsGained is less than 30:
		Say "[one of][backfield route] who spins away from [one of the linebackers] and follows his blockers down the field before juking [one of the deep pass defenders] and stepping into the endzone for a touchdown[or][short route] who makes the catch and then beats everyone to the endzone for a touchdown[or][short route] who jukes [one of the linebackers] easily and then beats [one of the deep pass defenders] to the endzone for a touchdown[or][short route] who races down the sidelines and manages to drag [one of the linebackers] several yards into the endzone for a touchdown[or][medium route] for a quick-strike touchdown[or][medium route] who then steps into the endzone for an easy touchdown[or][medium route] who spins away from a would-be tackler and runs it in for a touchdown[or][medium route] who makes a nice catch and then vaults over a diving tackler into the endzone for a touchdown[or][medium route] who plucks the ball from the air and then races to the endzone for a touchdown[or][medium route] who makes the catch and stiff-arms [one of the deep pass defenders] out of the way before high-stepping into the endzone[or][medium route] who makes the catch and then drags a tackler into the endzone for a touchdown[at random]";
	otherwise:
		Say "[one of][medium route] who snatches the ball out of the air then makes several defenders miss as he weaves his way down the field to the endzone for a touchdown[or][medium route]who catches the ball on the run and beats [one of the deep pass defenders] in a race to the end zone for a touchdown[or][medium route]who makes a tough catch and then spins away from [one of the deep pass defenders] before accelerating away to the endzone for a touchdown[or][medium route]who slips away from [one of the deep pass defenders] and runs away from the defense for a touchdown[or][deep route] in the endzone for a long range touchdown[or][deep route] who then steps easily into the endzone[or][deep route] who then easily outruns his man to the endzone for a touchdown[or][deep route] who collects the ball nicely and jukes [one of the deep pass defenders] before stepping into the endzone for a touchdown[or][deep route] who makes a beautiful catch in the endzone for a touchdown[at random]";

To say pass completion:
	if PlayResult is:
		-- "no gain":
			Say "[the play report prefix] quarterback [no gain pass]";
		-- "gain":
			if PlayDistance is:
				-- "short":
					Say "[the play report prefix] quarterback [short pass]";
				-- "medium":
					Say "[the play report prefix] quarterback [medium pass]";
				-- "big":
					Say "[the play report prefix] quarterback [big pass]";			
		-- "loss":
			Say "[the play report prefix] quarterback [loss pass]";

To say no gain:
	if PlayCalled is:
		-- "run":
			Say "[run play]";
		-- "pass":
			Say "[pass completion]";	

To say loss:
	if PlayCalled is:
		-- "run":
			Say "[run play]";
		-- "pass":
			Say  "[pass completion]";	
	
To say gain:
	if PlayCalled is:
		-- "run":
			Say "[run play]";
		-- "pass":
			Say  "[pass completion]";
	


[ POSITIVE PLAY RESULTS ]
		
To say first down:
	if PlayCalled is:
		-- "run":
			Say "[run play]. [one of]He leaps up and enthusiastically signals the first down[or]He pops back up and gives the hand signal for a first down[or]He pumps his fist after picking up a first down[or]He nods to his teammates as they acknowledge the first down effort[or]He throws the ball to the referee after getting the first down signal[or]He signals first down to the defensive players[or]He walks back to his teammates and they high-five him for the first down[or]He places the ball emphatically down to signal the first down[or]He nods excitedly about passing the first down marker[or]He points back to the first down marker and nods at the defense[or]He screams something incoherent about getting a first down[at random]";
		-- "pass":
			Say "[pass completion]. [one of]He leaps up and enthusiastically signals the first down[or]He pops back up and gives the hand signal for a first down[or]He pumps his fist after picking up a first down[or]He nods to his teammates as they acknowledge the first down effort[or]He throws the ball to the referee after getting the first down signal[or]He signals first down to the defensive players[or]He walks back to his teammates and they high-five him for the first down[or]He places the ball emphatically down to signal the first down[or]He nods excitedly about passing the first down marker[or]He points back to the first down marker and nods at the defense[or]He screams something incoherent about getting a first down[at random]";

To say touchdown:
	if PlayCalled is:
		-- "run":
			Say "[the play report prefix] running back [run type] [touchdown run]";
		-- "pass":
			Say "[the play report prefix] quarterback [touchdown pass]";



[ NEGATIVE PLAY RESULTS ]

To say incompletion:
	Say "[the play report prefix] quarterback [one of][backfield route][or][short route][or][medium route][at random] [one of]but it falls incomplete[or]but misses him badly[or]but overthrows him[or]but it's juggled and dropped[or]but it hits the ground at the receiver's feet[or]but it falls incomplete[or]but it skips on the ground at the receiver's feet[or]but it's behind the receiver[or]but misses him high[or]but the timing is off and it falls incomplete[or]but the receiver can't make the tough catch[or]but there's a mix-up and the receiver runs a different route, it's incomplete[or]but it's knocked away by [one of the pass defenders][or]but [one of the pass defenders] has perfect coverage and it falls incomplete[or]but the receiver can't make the catch under heavy pressure from [one of the pass defenders][or]but it falls incomplete thanks to the efforts of [one of the pass defenders][or]but the pass is deflected by [one of the linebackers] sitting back in a zone[or]but [one of the linebackers] knocks it away[or]but it bounces off the receivers knee and falls incomplete[or]but it's not accurate enough and the receiver can't make the catch[or]but it's a little off and the receiver can't adjust in time to make the grab[or]but the receiver is defended perfectly by [one of the pass defenders] and can't make the catch[or]but [one of the pass defenders] is right there and knocks it away from the receiver[or]but [one of the pass defenders] makes a great play to knock it away[or]but [one of the pass defenders] has perfect coverage on the play and it's incomplete[at random]";

To say sack:
	Say "[the play report prefix] quarterback [one of]drops back[or]takes the snap[or]drops back and surveys the field[or]spots an open receiver[or]takes a short dropback[or]takes a three-step drop[or]takes a deep drop[or]gets the ball[at random] [one of]but is hit almost immediately by [one of the pass rushers] who comes through the line untouched[or]but can't find a receiver and is buried by [one of the pass rushers] after holding onto the ball too long[or]but is chased down and sacked by [one of the pass rushers][or]but is dragged down before he can get the ball away by [one of the pass rushers][or]but is pulled down from behind by [one of the pass rushers][or]but gets crushed by [one of the pass rushers] bursting through the protection[or]but the protection breaks down and he's hurled down by [one of the pass rushers][or]but [one of the pass rushers]is on him before he can throw a pass and he goes down[or]but [one of the pass rushers]catches him from the blind side and takes him down[or]but [one of the pass rushers] levels him from the blindside[or]but [one of the pass rushers] slips past a blocker and drags him down[or]but [one of the pass rushers] tears through the offensive line and wraps him up[at random]";
	
To say safety:
	if PlayCalled is:
		-- "run":
			Say "[run play]. The tackle is made in the endzone for a safety";
		-- "pass":
			if PlayResult is:
				-- "loss":
					Say "[pass completion]. The tackle is made in the endzone for a safety";
				-- "sack":
					Say "[sack]. The hit is made in the endzone for a safety";
					

To say fumble:
	if PlayCalled is:
		-- "run":
			Say "[run play]. [one of]He loses the ball in the tackle and [one of the linebackers] picks it up[or]The ball is punched out in the tackle and [one of the linebackers] snaps it up[or]The ball is stripped in the tackle and [one of the pass defenders] grabs it[at random]";
		-- "pass":
			if PlayResult is:
				-- "sack":
					Say "[sack]. The ball is stripped during the sack and [one of the pass rushers] recovers the fumble";
				-- "otherwise":
					Say "[pass completion]. [one of]He loses the ball in the tackle and the [one of the deep pass defenders] picks it up[or]The ball is punched out in the tackle and [one of the pass defenders] snaps it up[or]The ball is stripped in the tackle and [one of the linebackers] grabs it[at random]";
					
	
To say fumble return touchdown:
	if NetYardsReturned is less than 10:
		Say "[fumble] and runs it into the endzone for an easy touchdown";
	otherwise if NetYardsReturned is less than 20:
		Say "[fumble] and manages to ellude a despairing tackle from the running back to run it in for a touchdown";
	otherwise:
		Say "[fumble] and rumbles along with a convoy of blockers all the way down the field for a touchdown";
					
To say interception:
	if PlayResult is:
		-- "no gain":
			Say "[the play report prefix] quarterback [one of][backfield route][or][short route][at random] [one of][one of the pass defenders] picks it off[or]but [one of the linebackers] steps in front of the ball and picks it off[or]but [one of the linebackers] intercepts it[or]but [one of the defensive linemen] makes a stunning interception[or]but [one of the linebackers] makes a diving interception[or]but [one of the linebackers] muscles the ball away from the receiver for an interception[or]but [one of the linebackers] tips the ball up and [one of the deep pass defenders] grabs the loose ball for an interception[at random]";
		-- "gain":
			if PlayDistance is:
				-- "short":
					Say "[the play report prefix] quarterback [short route] [one of]but [one of the pass defenders] picks it off[or]but [one of the pass defenders] gets between the receiver and the ball and picks it off[or]but [one of the linebackers] leaps high into the air to pick it off[or]but [one of the pass defenders] steps into the passing lane and makes an easy pick[or]but [one of the pass defenders] is sitting on the route and steps in front of it for the interception[or]but [one of the pass defenders] intercepts it[or]but [one of the linebackers] drills the receiver and he coughs up the ball and [one of the deep pass defenders] grabs it out of the air[or]but [one of the deep pass defenders] times his move perfectly and steps through for the interception[or]but [one of the deep pass defenders] flies through at the last moment to make the pick[or]but [one of the linebackers] anticipates the throw and leaps to make the interception[at random]";
				-- "medium":
					Say "[the play report prefix] quarterback [medium route] [one of]but [one of the pass defenders] picks it off[or]but [one of the pass defenders] sees the pass coming and makes an easy interception[or]but the receiver slips in his route and [one of the linebackers] is there to make an easy pick[or]but [one of the deep pass defenders] is lurking in a zone and steps in front of the pass for an interception[or]but [one of the pass defenders] muscles in front of the receiver to pick it off[or]but [one of the linebackers] makes a dazzling interception[or]but it bounces off the receiver's hands and [one of the deep pass defenders] snatches it out of the air[or]but [one of the deep pass defenders] has timed the route and steps through for an interception[at random]";
				-- "big":
					Say "[the play report prefix] quarterback [deep route] [one of]but [one of the deep pass defenders] picks it off[or]but [one of the deep pass defenders] reads it perfectly and is there to make the interception[or]but it's a poorly judged throw and [one of the deep pass defenders] picks it off[or]but [one of the deep pass defenders] manages to gain a strong position and makes the interception[or]but [one of the deep pass defenders] comes over to help on the route and makes the pick[or]but one of the deep safeties comes across and picks it off easily[or]but [one of the deep pass defenders] lurking in a deep zone manages to get in front of the receiver and pick it off[or]but it's thrown into a double team and [one of the deep pass defenders] comes up with the interception[at random]";
		-- "loss":
			Say "[the play report prefix] quarterback [backfield route] [one of]but [one of the defensive linemen] steps in front of it and makes the pick[or]but [one of the linebackers] out-muscles the receiver and comes up with the ball[or]but [one of the linebackers] has anticipated the player and steps through to make an interception[or]but [one of the deep pass defenders] is playing close to the line and jumps the pass for an interception[or]but [one of the linebackers] times his jump to snatch the ball out of the air for an interception[at random]";

To say interception return touchdown:
	if NetYardsReturned is less than 10:
		Say "[interception] and runs it back for an easy touchdown";
	otherwise if NetYardsReturned is less than 30:
		Say "[interception] then finds a seam and runs it back for a touchdown";
	otherwise:
		Say "[interception] then weaves his way down the field, eventually following a convoy of blockers in for a touchdown";

To say turnover on downs:
	if PlayCalled is:
		-- "run":
			if PlayResult is:
				-- "gain":
					Say "[run play], but it's not enough and the ball is turned over";
				-- "no gain":
					Say "[run play], failing to make the first down and turning it over";
				-- "loss":
					Say "[run play], leading to a turnover on downs";
		-- "pass":
			if PlayResult is:
				-- "sack":
					Say "[sack]. And it's a turnover on downs";
				-- "gain":
					Say "[pass completion], but it's not enough and the ball is turned over";
				-- "no gain":
					Say "[pass completion], failing to make the first down and turning it over";
				-- "loss":
					Say "[pass completion], leading to a turnover on downs";
				-- "incompletion":
					Say "[incompletion] for a turnover on downs";
	

[ KICKING PLAY RESULTS ]

To say punt kick:
	Say "[the play report prefix] punter [one of]takes the snap[or]receives the snap[or]controls a slightly wide snap[or]leaps to control a high snap[or]calmly takes the snap[or]receives the ball[or]takes the ball[or]safety controls the snap[at random] [one of]and launches a punt down the field[or]and booms a punt into the air and downfield[or]and launches a kick downfield[or]and swings his leg, hitting a nice punt downfield[or]and kicks it downfield[or]and launches the ball downfield before the pressure gets to him[or]and launches the ball high into the air and down the field[or]and punts the ball nicely downfield[or]and booms the ball up into the air and down the field[or]and kicks the ball deep downfield[or]and hammers the ball with a strong kick down the field[or]and launches the ball with a strong kick downfield[or]and nails a big kick downfield[or]and hits a good kick that spirals high into the air and down the field[or]and launches a viciously spinning punt that flies downfield[or]and launches a powerful kick down the field[or]and makes a big punt down the field[or]and launches a bullet of a kick down the field[at random]";
	
To say punt return:
	Say "[punt kick]. [one of]The return man makes a safe catch and runs it back a few yards before being leveled by one of the punt coverage unit[or]The return man dances away from an early tackle but can't get on track before being buried by the coverage team[or]The return man fields it nicely and spins away from the first tackler, picking up a few yards before being flattened by a linebacker[or]The return man side-steps the first tacklers and picks up a handful of yards before being tripped up[or]The return man sees a seam and races toward it but is leveled by a backup linebacker before he can pick up speed[or]The return man fights for a few yards before being dragged down[or]The return man is buried under a pile of bodies after taking a few strides[or]The return man tries to make some fancy moves, but is hit hard before he can get going[or]The return man makes the catch and accelerates forwards but is tripped up by a diving defender[or]The return man bobbles the catch and gets drilled by a flying defender before he can make much ground[or]The return man makes a safe catch of the ball and picks up some nice yards before he's knocked down by the coverage team[or]The return man fields the ball and spins out of a tackle, dancing for extra yards before he's driven into the ground by one of the coverage guys[or]The return man finds a seam in the coverage but is suddenly driven sideways by a strong tackle he didn't see coming[or]The return man is hit almost immediately by an enthusiastic coverage player[or]The return man jukes a coverage man but is brought down hard almost immediately[or]The return man manages to shake one tackler and fights forward for some tough yards[at random]";
	
To say punt touchback:
	Say "[punt kick], [one of]sending the ball bounding through the endzone[or]watching with annoyance as the ball bounces through the endzone[or]only to see the return man step aside and let the ball roll through the endzone[or]only to see it bounce once at the five yard line and bounce into the endzone[or]only to see the return man field it in the endzone and take a knee for a touchback[or]but it bounces through the endzone for a touchback[or]but the ball rolls into the endzone for a touchback[or]but he can't keep it in play and the ball bounces into the endzone for a touchback[or]but the ball skips through the endzone for a touchback[or]but the the return man lets it bounce into the endzone for a touchback[at random]";
	
To say kickoff kick:
	Say "[the play report prefix] kicker [one of]raises his arm to signal he's going to kick[or]signals to the kickoff team that he's going to kick[or]raises his arm[or]gives the signal[or]signals to his teammates[or]lifts his arm to signal[or]signals with a raised arm[or]signals[or]raises his hand to signal the kick[or]raises his hand[at random] [one of]then charges foward and launches the ball high and far downfield, the coverage team sprinting after it[or]then launches the ball down the field with his teammates sprinting after it[or]then boots the ball high and deep with the coverage team rushing down the field after it[or]then hammers the ball deep down the field with the coverage team in hot pursuit[or]and runs forward to kick the ball deep downfield with the coverage unit sprinting forward to cover it[or]and launches a booming kick down the field with the coverage unit chasing it[or]and hits a big kick down the field with the coverage unit chasing it down[or]and kicks it deep down the field with the coverage team sprinting after it[at random]";
	
To say kickoff return:
	Say "[kickoff kick]. [one of]The return man fields it cleanly and sets off upfield for a decent return before being crushed in a tackle by a backup linebacker[or]The return man secures the ball and sprints forward, juking one coverage player before being knocked off his feet by another[or]The return man makes a safe catch and then follows behind a wedge of return players, making a decent gain before being dragged down by the coverage team[or]The return man catches the ball and accelerate up the field, picking up good yards before being upended by a diving tackle[or]The return man catches the ball and sidesteps a speeding coverage player, making a decent gain before he's wrestled down[or]The return man fields the ball cleanly and runs up into the wedge formed by his teammates, picking up good yardage before being drilled by one of the coverage players[or]The return man sees a seam and accelerates into it only to be tripped up at the last moment by one of the coverage players[or]The return man spins away from the first wave of coverage players and zips up the sideline for a nice gain before he's shoved out of bounds[or]The return man runs left and gets to the sideline for a good gain before he's pushed out[or]The return man works off his blockers to weave up the middle of the field for a decent gain before being drilled by one of the coverage team[or]The return man works his way up the field very nicely before he's knocked down hard by a coverage player[or]The return man immediately veers left and tries to get to the sideline but is gang-tackled before he can really get going[or]The return man makes the catch and then tries his luck up the right side but is hit hard by one of the coverage players before he can do much damage[or]The return man stutter steps and makes several players miss before accelerating upfield only to be tripped by a diving coverage player[or]The return man fields the ball and heads right up the middle, making several players miss before he's brought down in a crushing tackle by a linebacker[at random]";
	
To say kickoff touchback:
	Say "[kickoff kick]. [one of]The return man lets the ball bounce through the endzone for a touchback[or]The return man fields the ball cleanly in the endzone and decides to take a knee rather than return it[or]The return man catchs the ball deep in the endzone and takes a knee[or]The ball flies over the return man's head and through the endzone for a touchback[or]The return man riskily lets the ball bounce at the five and it goes over his head and into the endzone for a touchback[or]The ball lands deep in the endzone for a touchback[or]The return man catchs the ball in his own endzone and takes a knee for a touchback[or]The return man steps aside as the ball bounces through the endzone for a touchback[or]The return man watches carefully as the ball lands in the endzone and bounces through for a touchback[or]The return man catches the ball deep in his endzone and decides to take a knee for the touchback[or]The return man lets the ball bounces through the endzone for a touchback[or]The return man watches at the ball sails over his head through the endzone for a touchback[at random]";
	
To say safety kickoff:
	Say "[the play report prefix] punter [one of]signals to the coverage team he's going to kick[or]signals[or]nods to the coverage team[or]signals to the coverage team[or]signals he's going to kick[or]gives the signal[or]signals the kick[or]gives the signal he's going to kick[at random] [one of]and kicks a booming punt down the field toward the return team with the coverage unit sprinting after it[or]and booms a punt down the field with the coverage unit in hot pursuit[or]and launches a punt downfield with the coverage unit sprinting after it[or]and hits a strong punt down the field witht he coverage team heading after it[or]and launches a strong punt downfield[at random]. [one of]The return man fields the ball and makes a nice return before being leveled[or]The return man fields it cleanly and dances through an open space in the coverage before being crushed in a tackle[or]The return man fields it and almost finds a break in the coverage but is wrestled down after a small gain[or]The return man makes the catch and then sets off up field for good yards before being knocked down by a diving tackle[at random]";

To say onside kick:
	Say "[the play report prefix] kicker [one of]gives the signal[or]signals[or]raises his arm to signal the kick[or]signals the kick[or]raises his arm[or]gives the kicking signal[at random] [one of]and rushes forward to kick, bouncing the ball high into the air toward the receiving team[or]and steps into the kick, bouncing the ball awkwardly toward the receiving team[or]and steps up to bounce the ball toward the receiving team[or]and kicks the ball downward into the turf, bouncing it high into the air toward the receiving team[or]and bounces the ball high into the air toward the receiving team[at random] [one of]with the chasers rushing after it[or]with the kickoff unit racing after it[or]with his teammates racing after it[or]with the rest of the team rushing to make the recovery[or]with the recovery team sprinting forward to get it[at random]";
	
To say successful onside kick:
	Say "[onside kick]. [one of]The ball slips out of the grasp of the return team and one of the recovery players falls on it for a successful onside kick[or]The ball skips away from the return team and one of the recovery players plucks it out of the air for the recovery[or]The ball ends up beneath an enormous pile of players and in the end it turns out the recovery team has it[or]The recovery team somehow knifes through the return unit and grabs the ball out of the air[or]The recovery team miraculously hauls it in[or]The balls skitters through the return team and the recovery unit is the first to it[at random]";
	
To say unsuccessful onside kick:
	Say "[onside kick]. [one of]One of the return team players immediately snatches the ball out of the arm and drops to the turf, protecting it carefully before being touched down by the recovery team[or]The ball is grabbed by one of the return team and he is bundled up in a tackle[or]The ball is chased down by one of the wide receivers on the return team and he picks up a couple of yards before being tackled[or]The ball goes straight to one of the wide receivers on the return team and he secures it before being knocked down by the recovery unit[or]One of the wide receivers on the return team falls on the ball as it skitters along and is touched down by the recovery team[at random]";
	
To say kick snap:
	Say "[the play report prefix] [one of]holder receives the snap, expertly turning the laces away,[or]holder secures the snap, setting it up perfectly,[or]holder receives a slightly wide snap but gets it into position[or]holder almost bobbles the ball but gets it down in time[or]holder takes the snap, setting up the ball nicely,[or]holder takes the snap, setting the ball up,[or]holder gets the snapped ball into position[or]holder secures the snaps[at random]";

To say an upright:
	Say "the [one of]left[or]right[at random] [one of]upright[or]goalpost[or]post[at random]";

To say successful field goal:
	if KickDistance is less than 30:
		Say "[kick snap] [one of]and the kicker nails the short field goal between the uprights[or]and the kicker hits it perfectly, sending it between the goalposts for the field goal[or]and the kicker strikes it well, sending it easily between the posts[or]and the kicker hits the short field goal without any trouble[or]and the kicker kicks it between the uprights with a minimum of fuss[or]and the kicker knocks it through without any difficulty[or]and the kicker hits a nice kick that sails through the uprights[or]and the kicker kicks it just inside [an upright][or]and the kicker's effort flies just inside [an upright][or]and the kick sails just inside [an upright][at random]";
	otherwise if KickDistance is less than 48:
		Say "[kick snap] [one of]and the kicker hits a nice kick, sending the ball straight between the uprights[or]and the kicker hits the field goal with plenty of distance to spare[or]and the kicker makes it just inside [an upright][or]and the kick is good, flying inside [an upright][or]and the kick is just good, grazing [an upright] on its way through[or]and the kicker hits the ball with good distance, bouncing it off [an upright] and in[or]and the kicker hits the ball well, sending the ball right through the uprights[or]and the kicker launches the ball between the uprights[or]and the kicker hits it with plenty of distance, squeezing it inside [an upright][at random]";
	otherwise if KickDistance is less than 56:
		Say "[kick snap] [one of]and the kicker hits it beautifully, the ball arcing straight between the uprights[or]and the kicker nails the kick, sending the ball between the uprights[or]and the kicker makes it look easy, splitting the uprights with a beautiful kick[or]and the kicker hits it with great power, squeezing the ball just inside [an upright][or]and the kicker hits a strong kick, getting it just inside [an upright][or]and the kicker hits it well, just getting it inside [an upright][at random]";
	otherwise:
		Say "[kick snap] [one of]and the kicker crushes it, launching the ball and clearing the cross bar for the long-range field goal[or]and the kicker booms a huge kick, squeezing it inside [an upright][or]and the kicker nails the kick, sending the ball just over the top of the cross bar[at random]";
	
To say unsuccessful field goal:
	if KickDistance is less than 30:
		Say "[kick snap] [one of]but the seemingly easy kick sails wide left[or]but the kick is no good, bouncing off [an upright] and going wide[or]but the kick hits [an upright] and bounces down into the endzone[or]but the kick is wide, flying outside [an upright] by a several feet[or]but the kick is just wide, grazing [an upright] on its way[or]but the simply seeming kick goes wide of [an upright][or]but the ball hits [an upright] and bounces away[or]but the ball sails wide[at random]";
	otherwise if KickDistance is less than 48:
		Say "[kick snap] [one of]but the kick isn't convincing and sails wide of [an upright][or]but the kicker can't make the kick, bouncing it off [an upright][or]and the kicker hits a nice kick, but it bounces out off [an upright][or]but the kick sails wide right[or]but the kicker doesn't connect well enough and the ball sails wide of [an upright][at random]";
	otherwise if KickDistance is less than 56:
		Say "[kick snap] [one of]but the kicker can't quite get hold of it well enough and the ball falls short of the cross bar[or]but the kicker slices it off his foot and the ball sails wide of [an upright][or]but the kicker doesn't hit it quite right and it sails wide[or]and the kick nails a nice-looking kick, but it bounces straight off [an upright] and back into the endzone[or]but the kicker mishits the ball and it flies wide of [an upright][at random]";
	otherwise:
		Say "[kick snap] [one of]but the kicker doesn't get enough power on the kick and the ball falls short of the cross bar[or]but the kicker can't get hold of it well enough and it falls short[or]but the kicker doesn't have the leg for such a big kick and it falls short[or]and the kicker hits an amazingly powerful kick, but it sails wide of [an upright][at random]";
	
To say successful extra point:
	Say "[kick snap] [one of]and the kicker makes the routine kick straight between the uprights[or]and the kicker slots it between the goal posts easily[or]and the kicker knocks it through without any difficulty[or]and the kick mishits it slightly, but squeezes it inside [an upright][or]and the kicked hits it well enough for it to get inside [an upright][or]and the kicker makes the easy kick for the extra point[or]and the kicker makes the extra point kick without any difficulty[or]and the kicker makes the kick with no trouble at all[or]and the kicker almost shanks the ball, bouncing the extra point in off [an upright][or]and the kicker knocks it through between the uprights[or]and the kicker makes the kick look as easy as it is, hitting the extra point[or]and the kicker knocks through the extra point without any trouble[or]and the kicker slots it between the goal posts easily[or]and the kicker knocks it through for the extra point[at random]";
	
To say unsuccessful extra point:
	Say "[kick snap] [one of]but somehow the kicker bounces the kick off [an upright][or]but somehow the kick goes wide, grazing the outside of [an upright][or]but the kick is blocked at the line by a leaping defensive lineman[or]but the easy kick inexplicably sails wide left[at random]";

To say two point conversion:
	Say "[the play report prefix] [one of]running back charges into the line[or]running back receives a flare pass from the quarterback[or]running back dances to the outside[or]running back dives[or]running back bulls his way into the line[or]running back follows his fullback's crushing block[or]tight end receives a quick out[or]fullback receives a quick screen[at random]";
	
To say successful two point conversion:
	Say "[two point conversion] [one of]and plunges into the endzone for a two point conversion[or]and steps untouched into the endzone for the two point conversion[or]and spins away from a tackler and into the endzone for the two point conversion[or]and jukes a defender on the goalline before stepping in for the two point conversion[or]and dives across the line for the two point conversion[at random]";

To say unsuccessful two point conversion:
	Say "[two point conversion] [one of]but is stopped with a crunching tackle just short of the goalline, failing the two point conversion[or]but slips and is brought down at the goalline[or]but is driven backwards in a brutal tackle by [one of the linebackers][or]but is immediately dragged down by [one of the defensive linemen][at random]";


[ TIME MANAGEMENT ]

To say spike the ball:
	Say "[the play report prefix] quarterback [one of]runs up to the line and spikes the ball quickly to stop the clock[or]hurries the offense up to the line and spikes the ball to stop the clock[or]signals to the offense to hurry to the line and spikes the ball to stop the clock[or]rushes the offense to the line and spikes the ball to stop the clock[or]rushes the offense forward to the line and spikes the ball to stop the clock[or]hurries to spike the ball to stop the clock[or]quickly takes the snap and spikes the ball to stop the clock[at random]";
	
To say take a knee:
	Say "[the play report prefix] quarterback [one of]waits until the play clock has almost completely run out and then takes the snap and drops to his knee to run the clock[or]drains the play clock as much as possible and then takes a knee[or]waits for the play clock to wind down and then takes a knee[or]takes a knee after letting the play clock almost expire[or]waits until the play clock has almost expired and then takes a knee[at random]";


[ CHANGEOVERS ]

To say start first half:
	Say "[the play report prefix] kickoff team run onto the field and set up for your kick, facing off against the return team";
	
To say start second half:
	Say "[the play report prefix] kickoff team run onto the field and set up for the kick, facing off against the return team";
	
To say start overtime:
	Say "[the play report prefix] kickoff team run onto the field and set up for the kick, facing off against the return team";

To say start next quarter:
	Say "The players on the field [one of]swap ends to get ready for[or]get ready for for[or]change sides for[at random] the [if Q is 2]second[otherwise if Q is 4]fourth[end if] quarter";
	
To say post two minute warning:
	Say "[one of]The players on the field mill around during the two minute warning break[or]The players stand around with their hands on their hips waiting for the end of the two minute warning break[or]The players discuss gameplans and strategy during the two minute break[or]The players huddle during the two minute break, discussing what needs to be done[or]The players wait out the two minute break, looking up at the jumbotron[at random]";



		
[ REPORT CROWD ]

Every turn:
	if PrePlayPossession is 1:
		if FinalPlayResult is "big gain" or (FinalPlayResult is "first down" and PlayResult is "big gain"):
			Change CrowdReport to "The crowd [one of]roars with excitement[or]roars[or]buzzes with excitement[or]erupts into a roar[or]erupts into applause[or]applauds and cries out wildly[or]cheers wildly[or]cheers rowdily[or]applauds and cheers loudly[or]roars its approval[or]roars its appreciation[at random]";
		otherwise if FinalPlayResult is "sack":
			Change CrowdReport to "The crowd [one of]makes a wincing sound[or]sucks in its breath in sympathy[or]sighs in pain[or]makes a pained sound[or]whispers with concern[or]goes quiet[at random]";
		otherwise if FinalPlayResult is "first down" or FinalPlayResult is "successful extra point":
			Change CrowdReport to "The crowd [one of]applauds[or]claps appreciatively[or]claps for the play on the field[or]applauds the play[or]ripples with applause[at random]";
		otherwise if FinalPlayResult is "touchdown":
			Change CrowdReport to "The crowd [one of]leaps to its feet and fireworks explode above the stadium[or]cries out excitedly[or]roars its approval of the play on the field[or]breaks into excited and incoherent chanting[or]breaks into the team song[or]shouts with joy and leaps to its feet applauding[or]bursts into rapturous applause and chanting[or]leaps up with a cry of joy[at random]";
		otherwise if FinalPlayResult is "interception" or FinalPlayResult is "fumble" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown":
			Change CrowdReport to "The crowd [one of]groans in unison[or]moans with concern[or]groans with despair[or]shouts with disbelief[or]shouts angrily[or]is suddenly silent and sad[or]groans in agony[or]moans with anguish[or]shouts incredulously[or]is suddenly deflated and quiet[at random]";
		otherwise if FinalPlayResult is "unsuccessful extra point":
			Change CrowdReport to "The crowd [one of]gasps[or]sits in shocked silence[or]shouts its disapproval[or]yells angrily[or]holds its many heads in its many hands[or]shudders[at random]";
		otherwise if FinalPlayResult is "successful two point conversion":
			Change CrowdReport to "The crowd [one of]cheers wildly[or]shrieks with excitement[or]bursts into rapturous applause[or]shouts with joy and hope[or]leaps to its feet applauding[at random]";
		otherwise if FinalPlayResult is "unsuccessful two point conversion":
			Change CrowdReport to "The crowd [one of]sighs[or]nods resignedly[or]mopes[or]slump deeper into their seats[or]look at each other sadly[or]lose a little more hope[at random]";
		otherwise if FinalPlayResult is "successful field goal":
			Change CrowdReport to "The crowd [one of]cheers and applauds[or]cheers you on[or]yells with happiness[or]stands and applauds[or]shouts with pleasure[or]shouts its approval[or]cries out its approval[or]yells happily[or]beams and applauds[or]applauds enthusiastically[at random]";
		otherwise if FinalPlayResult is "unsuccessful field goal":
			Change CrowdReport to "The crowd [one of]moans regretfully[or]sighs deeply[or]yells in anger[or]stirs restlessly[or]slump down into their seats[or]sighs[or]groans[at random]";
	otherwise:
		if FinalPlayResult is "big gain" or (FinalPlayResult is "first down" and PlayResult is "big gain"):
			Change CrowdReport to "The crowd [one of]murmurs moodily[or]groans[or]shouts with exasperation[or]winces as one[or]mutters[or]seethes[or]groans with frustration[or]sighs with resignation[or]sighs deeply[or]shouts with annoyance[or]murmurs with worry[at random]";
		otherwise if FinalPlayResult is "sack":
			Change CrowdReport to "The crowd [one of]cheers[or]applauds heartily[or]jumps up and cheers[or]applauds with enthusiasm[or]shouts with pleased surprise[or]yells its approval[or]applauds the play[or]shouts happily[or]shouts its approval[at random]";
		otherwise if FinalPlayResult is "touchdown":
			Change CrowdReport to "The crowd [one of]sits in deflated silence[or]sinks into a deathly silence[or]is rendered voiceless[or]sits back in shock[or]sighs with regret[or]yells with anger[or]moans and hushes[or]murmurs with concern[at random]";
		otherwise if FinalPlayResult is "interception" or FinalPlayResult is "fumble" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown":
			Change CrowdReport to "The crowd [one of]goes wild[or]shouts with joy[or]leaps to its feet and applauds[or]jumps up and shouts its approval[or]bursts into applause[or]applauds ecstatically[or]screams with excitement[or]shrieks with joy[at random]";
		otherwise if FinalPlayResult is "unsuccessful extra point":
			Change CrowdReport to "The crowd [one of]jeers[or]smirks[or]chuckles heartlessly[or]smiles inwardly[or]applauds sarcastically[at random]";
		otherwise if FinalPlayResult is "successful two point conversion":
			Change CrowdReport to "The crowd [one of]mutters[or]ripples with worry[or]cries out with concern[or]is hushed[or]shivers and worries[at random]";
		otherwise if FinalPlayResult is "unsuccessful two point conversion":
			Change CrowdReport to "The crowd [one of]cheers with relief[or]applauds and relaxes a little[or]voices its approval[or]roars with relief[at random]";
		otherwise if FinalPlayResult is "successful field goal":
			Change CrowdReport to "The crowd [one of]simmers[or]mutters[or]chatters nervously[or]sits tensely still[or]murmurs with worry[or]shifts uneasily[or]worries[at random]";
		otherwise if FinalPlayResult is "unsuccessful field goal":
			Change CrowdReport to "The crowd [one of]applauds sarcastically[or]smirks[or]nods with relief[or]wipes its brow[or]exhales with relief[or]sighs with relief[at random]";		
			



Every turn:
	if WatchTheGame is "true":
		Say "[the play report].";
		Change WatchTheGame to "false";
		
Every turn:
	if CrowdReport is not "nothing":
		Say "[CrowdReport].";
		Change CrowdReport to "nothing";
		
Every turn:
	if RefereeReport is not "nothing":
		Say "[RefereeReport].";
		Change RefereeReport to "nothing";
		
Every turn:
	if TimeoutReport is not "nothing":
		Say "[TimeoutReport].";
		Change TimeoutReport to "nothing";









Chapter - The status line

When play begins:
	Change the left hand status line to "[the player's surroundings], Q1, 15:00, 1st & 0 @ HOM 30";
	if PostPlayPossession is 1:
		Change the right hand status line to "o HOM [HomeScore] - [AwayScore] AWY";
	otherwise:
		Change the right hand status line to "HOM [HomeScore] - [AwayScore] AWY o";
			
Every turn:
	if Clock is not "end of game":
		Change TheMinutes to T divided by 60;
		Change TheSeconds to the remainder after dividing T by 60;
		if PostPlayPossession is 1:
			if PostPlayFieldPosition is less than 50:
				Change PostPlayFieldPositionText to "HOM [PostPlayFieldPosition]";
			otherwise if PostPlayFieldPosition is greater than 50:
				Change PostPlayFieldPositionText to "AWY [100 - PostPlayFieldPosition]";
			otherwise:
				Change PostPlayFieldPositionText to "50";
		otherwise:
			if PostPlayFieldPosition is less than 50:
				Change PostPlayFieldPositionText to "AWY [PostPlayFieldPosition]";
			otherwise if PostPlayFieldPosition is greater than 50:
				Change PostPlayFieldPositionText to "HOM [100 - PostPlayFieldPosition]";
			otherwise:
				Change PostPlayFieldPositionText to "50";			
		Change the left hand status line to "[the player's surroundings], [if Q is less than 5]Q[Q][otherwise]OT[end if], [TheMinutes]:[if TheSeconds is less than 10]0[end if][TheSeconds], [if PostPlayDown is 1]1st[otherwise if PostPlayDown is 2]2nd[otherwise if PostPlayDown is 3]3rd[otherwise if PostPlayDown is 4]4th[end if] & [PostPlayDistance] @ [PostPlayFieldPositionText]";
		if PostPlayPossession is 1:
			Change the right hand status line to "o HOM [HomeScore] - [AwayScore] AWY";
		otherwise:
			Change the right hand status line to "HOM [HomeScore] - [AwayScore] AWY o";
	otherwise if Clock is "end of game":
		Change left hand status line to "[the player's surroundings], End of Game";
		Change the right hand status line to "HOM [HomeScore] - [AwayScore] AWY";








Part - Ending the Game

[ CHECK OVERTIME VICTORY]

Every turn:
	if Q is 5:
		if FinalPlayResult is "touchdown" or FinalPlayResult is "successful field goal":
			Change Clock to "end of game";
		otherwise if FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "safety":
			Change Clock to "end of game";

Every turn:
	if Clock is "end of game":
		if HomeScore is greater than AwayScore:
			End the game saying "You won the game. You scored [score] points in [turn count] turns.";
		otherwise if AwayScore is greater than HomeScore:
			End the game saying "You lost the game. You scored [score] points in [turn count] turns.";
		otherwise:
			End the game saying "It's a draw. You scored [score] points in [turn count] turns.";
		if VerboseMode is "VERBOSE":
			Say "HOME: [line break]";
			Say "[HPC]/[HPA] passing for [HPY] yards, [HPTD] TDs and [HPI] INTs [line break]";
			Say "[HRA] rushes for [HRY] yards and [HRTD] TDs [line break]";
			Say "[HFGM]/[HFGA] field goals ";
			Say "[HXPM]/[HXPA] extra points ";		
			Say "AWAY: [line break]";
			Say "[APC]/[APA] passing for [APY] yards, [APTD] TDs and [API] INTs [line break]";
			Say "[ARA] rushes for [ARY] yards and [ARTD] TDs [line break]";
			Say "[AFGM]/[AFGA] field goals ";
			Say "[AXPM]/[AXPA] extra points ";





Part - Calling the next play


		

Chapter - Situational play calling modes (in which we hurry up, eat the clock, or normal)

[ CHECK FOR SITUATIONAL MODES ]

Every turn:
	If (Q is 2 or Q is 4 or Q is 5) and T is less than 450:
		if OffenceTrailingBy is greater than 0:
			Change OffenceMode to "hurry up";
			Change DefenceMode to "eat the clock";
		otherwise if OffenceTrailingBy is less than 0:
			Change OffenceMode to "eat the clock";
			Change DefenceMode to "hurry up";
		otherwise:
			Change OffenceMode to "hurry up";
			Change DefenceMode to "hurry up";
		if (OffenceMode is "hurry up"):
			Change RunChance to 15;
		otherwise:
			Change RunChance to 47;
		
	


Chapter - Calling a Play (in which we work out what the team with the ball should do)

[ CALL A PLAY ------------------------------------------------------------------------------------------------]

Every turn:
	Change PreviousPlayCalled to PlayCalled;


[ NORMAL CALL ]

Every turn:
	if PostPlayDown is 1 or PostPlayDown is 2 or PostPlayDown is 3:
		[ NORMAL PLAY DOWN ]
		Let Rand be a random number between 0 and 100;
		if Rand <= RunChance:
			Change PlayCalled to "run";
		otherwise:
			Change PlayCalled to "pass";
	otherwise if PostPlayDown is 4:
		[ FOURTH DOWN ]
		 if PostPlayFieldPosition is greater than 65:
			Change PlayCalled to "field goal";
		otherwise:
			Change PlayCalled to "punt";
	If DebugMode is "DEBUG" then say "INITIAL PLAY: [PlayCalled] [line break]";



[ MODIFIED CALLS - WHERE WE CHANGE THE PLAY CALLED TO SOMETHING SITUATIONALLY DEPENDENT ]

[ GO FOR IT ]

Every turn:
	If Q is 4 and OffenceMode is "hurry up" and PostPlayDown is 4 and OffenceTrailingBy is greater than 0:
		if OffenceTrailingBy is less than 4 and T is less than 120:
			Change PlayCalled to "pass";
			If DebugMode is "DEBUG" then say "Special case: Need field goal in final two minutes: GO FOR IT";
		otherwise if OffenceTrailingBy is less than 9 and T is less than 120:
			Change PlayCalled to "pass";
			If DebugMode is "DEBUG" then say "Special case: Need TD final two minutes: GO FOR IT";
		otherwise if T is less than (60 + (90 * (OffenceTrailingBy divided by 7))) and PostPlayFieldPosition is greater than 30:
			[ TRAILING BY TOUCHDOWNS AND NEED THE POSSESSIONS TO SCORE ENOUGH ]
			Change PlayCalled to "pass";
			If DebugMode is "DEBUG" then say "Special case: Trailing by [OffenceTrailingBy]: GO FOR IT";
					


[  SITUATIONAL FIELD GOALS ]

Every turn:
	if PlayCalled is "run" or PlayCalled is "pass" or PlayCalled is "punt":
		if Q is 5 and PostPlayFieldPosition is greater than 80:
			Change PlayCalled to "field goal";
			If DebugMode is "DEBUG" then say ">> OVERTIME FG";
		otherwise if Q is 2 and T is less than 45 and PostPlayFieldPosition is greater than 60:
			Change PlayCalled to "field goal";			
			If DebugMode is "DEBUG" then say ">> END OF Q2 FG";
		otherwise if Q is 4 and T is less than 45 and PostPlayFieldPosition is greater than 60 and OffenceTrailingBy is less than 4 and OffenceTrailingBy is greater than -1:
			Change PlayCalled to "field goal";
			If DebugMode is "DEBUG" then say ">> END OF Q4 FG TO TIE OR WIN";
		


[ TAKE A KNEE ]

Every turn:
	if PlayCalled is "run" or PlayCalled is "pass" or PlayCalled is "field goal" or PlayCalled is "player field goal":
		if Q is 4 and OffenceMode is "eat the clock" and PostPlayFieldPosition is greater than 2:
			if (((4 - PostPlayDown) * 40) - (DefenceTimeOuts * 40)) is greater than T:
				Change PlayCalled to "take a knee";
				If DebugMode is "DEBUG" then say ">> TAKE A KNEE";
				
	

[ SPIKE THE BALL ]

Every turn:
	If PlayCalled is "run" or PlayCalled is "pass" or PlayCalled is "field goal":
		if Q is 4 and OffenceMode is "hurry up" and OffenceTimeouts is 0 and (PostPlayDown is 1 or PostPlayDown is 2 or PostPlayDown is 3) and Clock is "running" and NetYardsGained is greater than 20:
			Change PlayCalled to "spike the ball";
			if DebugMode is "DEBUG" then say ">> SPIKE THE BALL";
		
	
	
	
[ EXTRA POINT ]

Every turn:
	if FinalPlayResult is "touchdown" or FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "kickoff return touchdown":
		Change PlayCalled to "extra point";
		if (Q is 4 and T is less than 180) and (OffenceTrailingBy - 2 is 0 or OffenceTrailingBy - 2 is 3 or OffenceTrailingBy - 2 is 7):
			Change PlayCalled to "two point conversion";
			If DebugMode is "DEBUG" then say ">> TWO POINT CONVERSION";
	otherwise if FinalPlayResult is "start next quarter" and (PreviousPlayResult is "touchdown" or PreviousPlayResult is "interception return touchdown" or PreviousPlayResult is "fumble return touchdown" or PreviousPlayResult is "kickoff return touchdown"):
		Change PlayCalled to "extra point";
		if (Q is 4 and T is less than 180) and (OffenceTrailingBy - 2 is 0 or OffenceTrailingBy - 2 is 3 or OffenceTrailingBy - 2 is 7):
			Change PlayCalled to "two point conversion";
			If DebugMode is "DEBUG" then say ">> TWO POINT CONVERSION";
	otherwise if FinalPlayResult is "two minute warning" and (PreviousPlayResult is "touchdown" or PreviousPlayResult is "interception return touchdown" or PreviousPlayResult is "fumble return touchdown" or PreviousPlayResult is "kickoff return touchdown"):
		Change PlayCalled to "extra point";
		if (Q is 4 and T is less than 180) and (OffenceTrailingBy - 2 is 0 or OffenceTrailingBy - 2 is 3 or OffenceTrailingBy - 2 is 7):
			Change PlayCalled to "two point conversion";
			If DebugMode is "DEBUG" then say ">> TWO POINT CONVERSION";
			
			
		
	
	

[ KICKOFF ]

Every turn:
	[ STANDARD KICKOFF SITUATIONS ]
	If FinalPlayResult is "successful extra point" or FinalPlayResult is "unsuccessful extra point" or FinalPlayResult is "miskicked extra point" or FinalPlayResult is "successful field goal" or FinalPlayResult is "successful player field goal" or FinalPlayResult is "start first half" or FinalPlayResult is "start second half" or FinalPlayResult is "start overtime" or FinalPlayResult is "successful two point conversion" or FinalPlayResult is "unsuccessful two point conversion":
		Change PlayCalled to "kickoff";
		If DebugMode is "DEBUG" then say ">> KICKOFF";
	[ POST QUARTER CHANGE KICKOFF SITUATIONS ]
	otherwise if FinalPlayResult is "start next quarter" and (PreviousPlayResult is "successful extra point" or PreviousPlayResult is "unsuccessful extra point" or PreviousPlayResult is "successful field goal" or PreviousPlayResult is "successful player field goal" or PreviousPlayResult is "miskicked extra point" or PreviousPlayResult is "successful two point conversion" or PreviousPlayResult is "unsuccessful two point conversion"):
		Change PlayCalled to "kickoff";
		If DebugMode is "DEBUG" then say ">> KICKOFF";
	[ POST TWO MINUTE WARNING KICKOFF SITUATIONS ]
	otherwise if FinalPlayResult is "two minute warning" and (PreviousPlayResult is "successful extra point" or PreviousPlayResult is "unsuccessful extra point" or PreviousPlayResult is "successful field goal" or PreviousPlayResult is "successful player field goal" or PreviousPlayResult is "miskicked extra point" or PreviousPlayResult is "successful two point conversion" or PreviousPlayResult is "unsuccessful two point conversion"):
		Change PlayCalled to "kickoff";
		If DebugMode is "DEBUG" then say ">> KICKOFF";		
	[ STANDARD SAFETY KICKOFF SITUATIONS ]	
	otherwise if FinalPlayResult is "safety":
		Change PlayCalled to "safety kickoff";
		If DebugMode is "DEBUG" then say ">> SAFETY KICKOFF";		
	[ POST QUARTER CHANGE SAFETY KICKOFF SITUATIONS ]
	otherwise if FinalPlayResult is "start next quarter" and PreviousPlayResult is "safety":
		Change PlayCalled to "safety kickoff";
		If DebugMode is "DEBUG" then say ">> SAFETY KICKOFF";		
	[ POST TWO MINUTE WARNING SAFETY KICKOFF SITUATIONS ]
	otherwise if FinalPlayResult is "two minute warning" and PreviousPlayResult is "safety":
		Change PlayCalled to "safety kickoff";
		If DebugMode is "DEBUG" then say ">> SAFETY KICKOFF";		


Every turn:
	If Clock is "start first half"  or Clock is "start of second half" or Clock is "start of overtime":
		Change PlayCalled to "kickoff";
		If DebugMode is "DEBUG" then say ">> KICKOFF";		



[ ONSIDE KICK CALL ]

Every turn:
	if OffenceTrailingBy is greater than 0 and Q is 4 and T is less than 180 and PlayCalled is "kickoff":
		Change PlayCalled to "onside kick";
		If DebugMode is "DEBUG" then say ">> ONSIDE KICK because trailing by [OffenceTrailingBy]";
	


[ AUTOMATIC FOLLOWING PLAYS ]

Every turn:
	if Clock is "pregame":
		Change PlayCalled to "kickoff";
	otherwise if Clock is "end of quarter":
		Change PlayCalled to "start next quarter";
	otherwise if Clock is "end of half":
		Change PlayCalled to "start second half";
	otherwise if Clock is "two minute warning":
		Change PlayCalled to "post two minute warning";
	otherwise if Clock is "end of regulation":
		Change PlayCalled to "start overtime";
	
	

Every turn:
	If DebugMode is "DEBUG" then say "ACTUAL PLAY: [PlayCalled] [line break]";
	



Every turn:
	if KickStatus is "kicked" or KickStatus is "made tackle" or KickStatus is "missed tackle" or KickStatus is "no tackle":
		Change KickStatus to "post kick";

[ CONVERT KICKING PLAYS ]

Every turn:
	if KickMode is "player" and KickStatus is not "kicked" and PostPlayPossession is 1:
		if PlayCalled is "kickoff":
			Change PlayCalled to "player kickoff";
		if PlayCalled is "onside kick":
			Change PlayCalled to "player onside kick";
		if PlayCalled is "field goal":
			Change PlayCalled to "player field goal";
		if PlayCalled is "extra point":
			Change PlayCalled to "player extra point";
			


Every turn:
	if PlayCalled is "player kickoff":
		Change KickType to "kickoff";
		if KickStatus is "off the field" then change KickStatus to "get on the field";
		if KickStatus is "miskicked" then change KickStatus to "pending signal";
		if KickStatus is "post kick" then change KickStatus to "pending signal";
	otherwise if PlayCalled is "player onside kick":
		Change KickType to "onside kick";
		if KickStatus is "off the field" then change KickStatus to "get on the field";
		if KickStatus is "post kick" then change KickStatus to "pending signal";
	otherwise if PlayCalled is "player field goal":
		Change KickType to "field goal";
		if KickStatus is "off the field" then change KickStatus to "get on the field";
	otherwise if PlayCalled is "player extra point":
		Change KickType to "extra point";
		if KickStatus is "off the field" then change KickStatus to "get on the field";



Chapter - Team Unit Movement and Reporting

[ BASED ON PREVIOUS PLAY RESULT ]

TeamOn is text that varies. TeamOn is "".
TeamOff is text that varies. TeamOff is "".
TeamMovement is text that varies. TeamMovement is "".


Every turn:
	Change TeamOn to "nothing";
	Change TeamOff to "nothing";
	Change TeamMovement to "nothing";


Every turn:
	if FinalPlayResult is "punt" or FinalPlayResult is "punt touchback":
		if PrePlayPossession is 1:
			Move the punter to the sidelines;
			Move the special teams unit to the sidelines;
			Change TeamOff to "The punter and the punt team run off the field.";
		otherwise:
			Change TeamOff to "The punt return unit runs off the field.";
	otherwise if FinalPlayResult is "kickoff return" or FinalPlayResult is "kickoff touchback" or FinalPlayResult is "kickoff return touchdown" or FinalPlayResult is "kickoff return tackle":
		if PrePlayPossession is 1:
			Move the special teams unit to the sidelines;
			Change TeamOff to "The kickoff unit runs off the field.";
		otherwise:
			Move the special teams unit to the sidelines;
			Change TeamOff to "The kick return team runs off the field.";
	otherwise if FinalPlayResult is "safety kickoff return":
		if PrePlayPossession is 1:
			Move the special teams unit to the sidelines;
			Change TeamOff to "The safety kickoff unit runs off the field.";
		otherwise:
			Move the special teams unit to the sidelines;
			Change TeamOff to "The safety kick return team runs off the field.";			
	otherwise if FinalPlayResult is "successful two point conversion" or FinalPlayResult is "unsuccessful two point conversion":
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";
	otherwise if FinalPlayResult is "successful extra point" or FinalPlayResult is "unsuccessful extra point" or FinalPlayResult is "miskicked extra point":
		if PrePlayPossession is 1:
			Move the special teams unit to the sidelines;
			Move the holder to the sidelines;			
			Change TeamOff to "The extra point unit runs off the field.";
		otherwise:
			Move the special teams unit to the sidelines;
			Change TeamOff to "The kick blocking unit runs off the field.";	
	otherwise if FinalPlayResult is "successful field goal" or FinalPlayResult is "unsuccessful field goal" or FinalPlayResult is "miskicked field goal":
		if PrePlayPossession is 1:
			Move the special teams unit to the sidelines;
			Move the holder to the sidelines;
			Change TeamOff to "The field goal team runs off the field.";
		otherwise:	
			Move the special teams unit to the sidelines;
			Change TeamOff to "The kick blocking unit runs off the field.";
	otherwise if FinalPlayResult is "touchdown":
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";
	otherwise if FinalPlayResult is "interception return touchdown" or FinalPlayResult is "fumble return touchdown" or FinalPlayResult is "interception" or FinalPlayResult is "fumble" or FinalPlayResult is "interception touchback":
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";
	otherwise if FinalPlayResult is "safety":
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";
	otherwise if FinalPlayResult is "turnover on downs":
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";
	otherwise if FinalPlayResult is "start next quarter" or FinalPlayResult is "start first half" or FinalPlayResult is "start overtime":
		Do nothing;
	otherwise if PostPlayDown is 4:
		[ DEFAULT FOR FOURTH DOWN IS TO RUN OFF - BUT COULD BE SENT BACK OUT ]
		if PrePlayPossession is 1:
			Move the offensive unit to the sidelines;
			Change TeamOff to "The offense runs off the field.";
		otherwise:
			Move the defensive unit to the sidelines;
			Change TeamOff to "The defense runs off the field.";

	
[ BASED ON PLAY CALLED ]
	
Every turn:
	if FinalPlayResult is "kickoff tackle":
		do nothing;
	otherwise if PlayCalled is "kickoff" and (PreviousPlayCalled is "extra point" or PreviousPlayCalled is "field goal"):
		[ POST SCORING PLAY KICKOFF ]
		Move the special teams unit to the field;	
		Change TeamOn to "The kickoff return unit runs onto the field.";
	otherwise if PlayCalled is "kickoff":
		[ MUST BE POST HALF-TIME KICKOFF OR OVERTIME KICKOFF ]
		Move the special teams unit to the field;
		Change TeamOn to "The kick return team runs onto the field.";
	otherwise if PlayCalled is "punt":
		if PostPlayPossession is 1:
			Move the punter to the field;
			Move the special teams unit to the field;			
			Change TeamOn to "The punt team runs onto the field.";
		otherwise:
			Move the special teams unit to the field;		
			Change TeamOn to "The punt return team runs onto the field.";
	otherwise if PlayCalled is "extra point":
		if FinalPlayResult is "touchdown" or FinalPlayResult is "kickoff return touchdown":
			Move the special teams unit to the field;
			Change TeamOn to "The kick blocking team runs onto the field.";
	otherwise if PlayCalled is "field goal":
		Move the special teams unit to the field;
		Change TeamOn to "The kick blocking unit runs onto the field.";
	otherwise if PlayCalled is "safety kickoff":
		if PostPlayPossession is 1:
			Move the special teams unit to the field;
			Change TeamOn to "The safety kick team runs onto the field.";
		otherwise:
			Move the special teams unit to the field;
			Change TeamOn to "The safety kick return team runs onto the field.";
	otherwise if PlayCalled is "run" or PlayCalled is "pass":
		if PostPlayDown is 4:
			if PostPlayPossession is 1:
				Move the offensive unit to the field;
				Change TeamOff to "nothing";
				Change TeamOn to "nothing";
			otherwise:
				Move the defensive unit to the field;
				Change TeamOff to "nothing";
				Change TeamOn to "nothing";
		otherwise:
			if PostPlayPossession is 1 and the offensive unit is not in the field:
				Move the offensive unit to the field;
				Change TeamOn to "The offense runs onto the field.";
			otherwise if PostPlayPossession is -1 and the defensive unit is not in the field:
				Move the defensive unit to the field;
				Change TeamOn to "The defense runs onto the field.";
				

[ BASED ON PLAYER-BASED PLAYS ]

Every turn:
	if PlayCalled is "player kickoff" and the special teams unit is not in the field:
		[ KICKING OFF AT START AND AFTER TWO POINT CONVERSION ]
		Move the special teams unit to the field;
		Change TeamOn to "The kickoff team runs onto the field.";
	otherwise if PlayCalled is "player onside kick" and the special teams unit is not in the field:
		[ KICKING OFF AFTER TWO POINT CONVERSION? ]
		Move the special teams unit to the field;
		Change TeamOn to "The onside kick team runs onto the field.";
	otherwise if PlayCalled is "player field goal" and the special teams unit is not in the field:
		Move the special teams unit to the field;
		Move the holder to the field;		
		Change TeamOn to "The field goal unit runs onto the field.";
	otherwise if PlayCalled is "player extra point":
		Move the special teams unit to the field;
		Move the holder to the field;		
		Change TeamOn to "The extra point unit runs onto the field.";



Every turn:
	if TeamOn is not "nothing":
		if TeamOff is not "nothing":
			say "[TeamOn] [TeamOff][line break]";
		otherwise:
			say "[TeamOn][line break]";
	otherwise if TeamOff is not "nothing":
		say "[TeamOff]";
		

[ RESPONDING TO PLAYER-BASED PLAYS ]

Chapter - Reporting player based plays

Every turn:
	if FinalPlayResult is "successful field goal" or FinalPlayResult is "successful extra point" or FinalPlayResult is "miskicked extra point":
		if PlayCalled is "player kickoff":
			Say "[one of]As you run back toward the kickoff line[or]As you head back to kickoff[or]As you head back for the kickoff[or]As you jog back to the 30 for the kickoff[or]As you run back to the 30 to kick off[or]As you make your way back up the field for the kickoff[at random] [one of]your coach signals from the sidelines for a standard kick[or]your coach signals for you to kick it deep[or]your coach gives the signal for a standard kick[or]your coach gives the sign to kick it deep[or]the coach signals for a deep kick[at random]. [one of]You stand 10 yards back from the ball[or]You position yourself for the kick[or]You get into position[or]You stand back from the ball[or]You set up for the kick[or]You get into your kicking position[or]You setup in position[or]You stand in position[at random] and [one of]your team[or]the coverage unit[or]the coverage team[or]the cover unit[or]the stadium[or]everyone[at random] waits for you to give the [bold type]signal[roman type].";
		otherwise if PlayCalled is "player onside kick":
			Say "[one of]As you run back toward the kickoff line[or]As you head back to kickoff[or]As you head back for the kickoff[or]As you jog back to the 30 for the kickoff[or]As you run back to the 30 to kick off[or]As you make your way back up the field for the kickoff[at random] [one of]your coach signals from the sidelines for an onside kick[or]your coach signals for you to perform an onside kick[or]your coach gives the signal for an onside kick[or]your coach gives the sign to perform an onside kick[or]your coach signals for an onside kick[at random]. [one of]You stand a few steps away from the ball[or]You position yourself near the ball[or]You get yourself ready[or]You stand in position[or]You get into position[or]You position yourself[at random] and [one of]everyone[or]the recovery team[or]the recover unit[at random] waits for you to give the [bold type]signal[roman type].";
	otherwise if (FinalPlayResult is "kickoff return" or FinalPlayResult is "kickoff touchback" or FinalPlayResult is "kickoff return touchdown" or FinalPlayResult is "kickoff return tackle") and player is in the field:
		Say "Your coach [one of]waves at you[or]signals to you[or]gestures at you[or]nods at you[or]beckons you[at random] to [bold type]run off the field[roman type].";
	otherwise if PlayCalled is "player kickoff" and KickStatus is "off the field":
		Change KickStatus to "get on the field";
		Say "Your coach [one of]grabs you by the arm[or]whacks you on the shoulder pads[or]smacks you on the shoulder[or]puts his hand on your shouder[or]nods to you[or]waves you on[at random] and tells you to [bold type]run onto the field[roman type] and kick off deep.";
	otherwise if PlayCalled is "player onside kick" and KickStatus is "off the field":
		Change KickStatus to "get on the field";		
		Say "Your coach [one of]grabs you by the arm[or]whacks you on the shoulder pads[or]smacks you on the shoulder[or]puts his hand on your shouder[or]nods to you[or]waves you on[at random] and tells you to [bold type]run onto the field[roman type] and perform an onside kick. [one of]He points out that the entire team is counting on you[or]He emphasises the importance of getting the kick right[or]He demands a good kick[or]He looks in your eyes and tells you the team needs to you make a good kick[at random].";
	otherwise if PlayCalled is "player field goal" and KickStatus is "off the field":
		Change KickStatus to "get on the field";
		Say "Your coach [one of]grabs you by the arm[or]whacks you on the shoulder pads[or]smacks you on the shoulder[or]puts his hand on your shouder[or]nods to you[or]waves you on[at random] and tells you to [bold type]run onto the field[roman type] and [one of]hit a[or]nail the[or]knock through[or]make the[or]hit the[or]get the team a[or]net the team a[at random]field goal.";
	otherwise if PlayCalled is "player extra point" and KickStatus is "off the field":
		Change KickStatus to "get on the field";		
		Say "Your coach [one of]grabs you by the arm[or]whacks you on the shoulder pads[or]smacks you on the shoulder[or]puts his hand on your shouder[or]nods to you[or]waves you on[at random]  and tells you to [bold type]run onto the field[roman type] and [one of]make[or]hit[or]slot through[or]chip through[or]add[at random] the extra point.";

			



Part - Transitioning to next play

Every turn:
	if PostPlayPossession is not PrePlayPossession:
		if PostPlayPossession is 1:
			Change OffenceTrailingBy to AwayScore - HomeScore;		
		otherwise:
			Change OffenceTrailingBy to HomeScore - AwayScore;
		Let Temp be OffenceMode;
		Change OffenceMode to DefenceMode;
		Change DefenceMode to Temp;		
		Let Temp2 be OffenceTimeouts;
		Change OffenceTimeouts to DefenceTimeouts;
		Change DefenceTimeouts to Temp2;	
	
Every turn:
	if FinalPlayResult is "miskicked field goal" and KickStatus is "miskicked":
		Change KickStatus to "post kick";
	if FinalPlayResult is "miskicked extra point" and KickStatus is "miskicked":
		Change KickStatus to "post kick";	


Every turn:
	Change PrePlayFieldPosition to PostPlayFieldPosition;
	Change PrePlayDown to PostPlayDown;
	Change PrePlayDistance to PostPlayDistance;
	Change PrePlayPossession to PostPlayPossession;
	Change PreviousPlayNetYards to NetYardsGained;
	Change PreviousPlayResult to FinalPlayResult;
	Change FinalPlayResult to "no result";
	Change PlayDistance to "n/a";


