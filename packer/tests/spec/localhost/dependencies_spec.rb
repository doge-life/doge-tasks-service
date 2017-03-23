require 'spec_helper'

describe 'doge-tasks-service dependencies' do
  describe command('java -version') do
    its(:stderr) { should contain 'openjdk version "1.8' }
  end

  describe command("su - -c '$CATALINA_HOME/bin/catalina.sh version' ubuntu") do
    its(:stdout) { should contain 'Apache Tomcat/8.0'}
  end

  describe iptables do
    it { should have_rule('-A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080').with_table('nat') }
  end
end
